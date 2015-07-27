var routerApp = angular.module('app', ['ui.router', 'door3.css']);
var remoteServer = 'http://localhost:8080';
//var remoteServer = '';
var warName = 'Web-1.0-SNAPSHOT';
routerApp.config(function ($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    $httpProvider.defaults.withCredentials = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});
routerApp.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider
        .state('home', {
            url: '/home',
            views: {
                "": {
                    templateUrl: 'views/home.html',
                    css: 'css/registration.css'
                }
            }
        })
        .state('home.login', {
            url: '/login',
            //templateUrl: 'views/login.html',
            views: {
                "": {
                    templateUrl: 'views/login.html',
                    css: 'css/registration.css',
                    controller: function ($scope, $http, $state) {
                        $scope.master = {};
                        $scope.clickBtn = function (user) {
                            $scope.master = angular.copy(user);
                            $http({
                                //url : 'j_security_check',
                                url: remoteServer + '/' + warName + '/rest/j_security_check',
                                method: "POST",
                                data: "j_username=" + user.j_username +
                                "&j_password=" + user.j_password,
                                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                            });
                            $state.go('conference.list');
                        }

                    }
                }
            }
        })
        .state('home.register', {
            url: '/register',
            views: {
                "": {
                    templateUrl: 'views/registration.html',
                    css: 'css/registration.css',
                    controller: function ($scope, $http, $state) {
                        $scope.clickBtn = function (user) {
                            $http({
                                url: remoteServer + '/' + warName + '/rest/register',
                                method: "POST",
                                data: "login=" + user.username +
                                "&password=" + user.password +
                                "&role=" + user.role +
                                "&phone=" + user.phone +
                                "&email=" + user.email +
                                "&name=" + user.name,
                                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                            });
                            $state.go('home.login');
                        }
                    }
                }
            }


        })
        .state('logout', {
            url: '/logout',
            controller: function ($http, $log, $state) {
                $http.get(remoteServer + '/' + warName + '/rest/logout');
                $state.go('home.login');
                $log.log('logout');
            }
        })
        .state("conference", {
            url: '/conference',
            css: "css/style.css",
            views: {
                "": {
                    templateUrl: 'views/conference.html',
                },
                "leftmenu": {
                    template: 'views/leftmenu.html'
                }
            }
        })
        .state("conference.list", {
            url: '/list',
            views: {
                "content": {
                    templateUrl: "views/mainspace.html",
                    controller: function ($scope, $http) {
                        $scope.link = "conference.info({idconf:square.id})";
                        $scope.title = "Conferences";
                        $scope.buttons = false;
                        $scope.warName = "Web-1.0-SNAPSHOT";
                        $scope.server = "http://localhost:8080/";
                        $scope.sections = [];
                        //$scope.sections.list = [];
                        //$scope.sections.title = '';
                        var tmp = new Object();
                        tmp.list = [];
                        tmp.title = '';
                        $http.get($scope.server + $scope.warName + "/rest/conference/all")
                            .success(function (data) {
                                angular.forEach(data, function (elem) {
                                    tmp.list.push({
                                        header: elem.name,
                                        id: elem.id,
                                        text: elem.description,
                                        date: elem.startDate + " - " + elem.endDate
                                    });
                                });
                            });
                        $scope.sections.push(tmp);
                    }
                }
            },
            css: ['css/style.css', 'css/all.css']
        })
        .state("conference.info", {
            url: '/{idconf:[0-9]+}',
            views: {
                "content": {
                    templateUrl: "views/mainspace.html",
                    controller: function ($scope, $stateParams, $http, $log) {
                        $scope.link = "conference.speech({idspeech:square.id})";
                        $scope.warName = "Web-1.0-SNAPSHOT";
                        $scope.server = "http://localhost:8080/";
                        $http.get($scope.server + $scope.warName + "/rest/conference/show/" + $stateParams.idconf)
                            .success(function (data) {
                                $log.log(data);
                                $scope.title = data.name;
                                $scope.description = data.description;
                            });
                        $scope.buttons = false;

                        //$http.get(remoteServer + '/' + warName +  "/rest/whoami")
                        //    .then(function (response) {
                        //        alert(response);
                        //        $log.log(response);
                        //        $scope.buttons = (response.localeCompare('organizer'));
                        //    });

                        $http({
                            url: remoteServer + '/' + warName + "/rest/whoami",
                            method: "GET",

                        })
                            .success(function (data) {
                                $log.log(data);
                                $scope.buttons = ((data.role) == 'organizer');
                            });
                        $scope.sections = [];
                        var tmp = new Object();
                        tmp.list = [];
                        tmp.title = '';
                        $http.get(remoteServer + '/' + warName + '/rest/conference/speeches/' + $stateParams.idconf)
                            .success(function (data) {
                                angular.forEach(data, function (elem) {
                                    tmp.list.push({
                                        header: elem.topic,
                                        id: elem.id,
                                        text: elem.speaker.name,
                                        date: elem.startDate
                                    });
                                });
                                tmp.title = 'Список докладов';
                            });
                        $scope.sections.push(tmp);
                        $log.log($scope.sections);
                    }
                }
            },
            css: ['css/style.css', 'css/all.css']
        })
        .state("conference.speech", {
            url: '/speech/{idspeech:[0-9]+}',
            views: {
                "content": {
                    templateUrl: "views/mainspace.html",
                    controller: 'speechCtrl'
                }
            },
            css: ['css/style.css', 'css/all.css']
        })
        .state('conference.speech.question', {
            url: '/speech/{idspeech:[0-9]+}/ask',
            views: {
                'content': {
                    templateUrl: 'views/addQuestion.html',
                    controller: 'questionCtrl'
                }
            }
        })


        .state('translation', {
            url: '/translation',
            views: {
                '': {
                    templateUrl: 'views/translation.html',
                    controller: 'translationCtrl'
                }
            },

        })
        .state('profile', {
            url: '/profile',
            views: {
                "": {
                    templateUrl: 'views/conference.html',
                    controller: function () {

                    }
                }
            },
            css: "css/style.css"
        })
        .state('profile.info', {
            url: '/profile/{login[0-9a-zA-Z]+}',
            css: ['css/style.css', 'css/all.css'],
            views: {
                "content": {
                    templateUrl: "views/mainspace.html",
                    controller: function () {

                    }
                }
            }
        });
});

routerApp.controller('speechCtrl',function ($scope, $stateParams, $http, $log) {
    $scope.link = "conference.speech({idspeech:square.id})";
    $scope.warName = "Web-1.0-SNAPSHOT";
    $scope.server = "http://localhost:8080/";
    $http({
        url: $scope.server + $scope.warName + "/rest/speech/info",
        method: "GET",
        params: {id: $stateParams.idspeech}
    })
        .success(function (data) {
            $log.log(data);
            $scope.title = data.topic;
            $scope.description = data.speaker.name;
        });
    $scope.buttons = false;
    var show = false;
    $scope.sections = [];

    $http({
        url: remoteServer + '/' + warName + "/rest/whoami",
        method: "GET",

    })
        .success(function (data) {
            show = ((data.role) == 'moderator');
            $log.log('show1 ' + show);
            test(show);
            //$scope.buttons = show;
        });


    function test(value) {
        show = value;
        $log.log('show2 ' + show);
        if (show) {
            $http({
                url: $scope.server + $scope.warName + "/rest/question/unmoderated",
                method: "GET",
                params: {id: $stateParams.idspeech}
            })
                .success(function (data) {
                    var tmp = new Object();
                    tmp.list = [];
                    tmp.title = '';
                    $log.log('unmoderated ' + data[0].text);
                    angular.forEach(data, function (elem) {
                        tmp.list.push({
                            header: elem.text,
                            id: elem.id,
                            text: elem.answer,
                            //date: elem.startDate
                        });
                    });
                    tmp.title = 'Unmoderated questions';
                    $scope.sections.push(tmp);
                });
            $http({
                url: $scope.server + $scope.warName + "/rest/question/moderated",
                method: "GET",
                params: {id: $stateParams.idspeech}
            })
                .success(function (data) {
                    var tmp = new Object();
                    tmp.list = [];
                    tmp.title = '';
                    $log.log('moderated ' + data[0].text);
                    angular.forEach(data, function (elem) {
                        tmp.list.push({
                            header: elem.text,
                            id: elem.id,
                            text: elem.answer,
                            //date: elem.startDate
                        });
                    });
                    tmp.title = 'Questions';
                    $scope.sections.push(tmp);
                });

        }
    };
});

routerApp.controller('translationCtrl', function ($scope, $http) {

    $scope.period = 30;
    $scope.defaultperiod = 30;

    $('#showup').hide();

    $scope.reports = [
        "time",
        "text"
    ];
    $scope.reports = [];

    $scope.speechId = 2;
    $scope.lastId = 0;
    $http.get(remoteServer + '/' + warName + '/rest/speech/topic/' + $scope.speechId)
        .success(function (data) {
            $scope.title = data;
        });
    $http.get(remoteServer + '/' + warName + '/rest/trans/fbs/' + $scope.speechId)
        .success(function (data) {
            angular.forEach(data, function (elem) {
                $scope.reports.push({time: elem[2], text: elem[1]});
            });
            if (data.length > 0) {
                $scope.lastId = data[data.length - 1][0];
            }
        });

    $scope.autoUpdates = function () {
        if ($scope.autoUpdate) {
            $scope.more();
            if ($scope.myForm.$valid) {
                setTimeout($scope.autoUpdates, 1000 * $scope.period);
            } else {
                setTimeout($scope.autoUpdates, 1000 * $scope.defaultperiod);
            }

        }
    }

    $scope.more = function () {
        $http({
            url: remoteServer + '/' + warName + '/rest/trans/updates',
            method: "GET",
            params: {speechId: $scope.speechId, lastId: $scope.lastId}
        }).success(function (data) {
            angular.forEach(data, function (elem) {
                $scope.reports.push({time: elem[2], text: elem[1]});
            });
            if (data.length > 0) {
                $scope.lastId = data[data.length - 1][0];
            }
        });
    }

    $scope.changeCheckbox = function () {
        if ($scope.autoUpdate) {
            $('#showup').show();
            $scope.autoUpdates();
        } else {
            $('#showup').hide();
        }
    }
});

routerApp.controller('questionCtrl', function($scope, $http, $stateParams) {


    $scope.login = "";



    $scope.question = {
        text: "",
        userLogin: "",
        speechId: $stateParams.idspeech
    };


    $http.get($scope.server + $scope.warName + "/rest/whoami")
        .success(function (data) {
            $scope.login = data.login;
            alert(data);
            alert($scope.login)
        });

    $scope.addQuestion = function() {
        $scope.question.speechId = $scope.speechId;
        alert($scope.question.text);
        alert($scope.question.speechId);
        alert("clicked");

        $http({
            url: $scope.server + $scope.warName + '/rest/question/add',
            method: "POST",
            data: {
                text: $scope.question.text,
                speechId: $scope.question.speechId
            },
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });

    }
});