var routerApp = angular.module('app', ['ui.router', 'door3.css','ui.bootstrap', 'ui.bootstrap.datetimepicker']);
var remoteServer = 'http://localhost:8080';
//var remoteServer = '';
var warName = 'Web-1.0-SNAPSHOT';
routerApp.config(function ($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    $httpProvider.defaults.withCredentials = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});
routerApp.config(function ($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home/login');

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
            views: {
                "": {
                    templateUrl: 'views/login.html',
                    css: 'css/registration.css',
                    controller: function ($scope, $http, $state) {
                        $scope.master = {};
                        $scope.clickBtn = function (user) {
                            $scope.master = angular.copy(user);
                            $http({
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
        .state("conference.create", {
            url: '/create',
            css: 'css/style.css',
            views: {
                "content": {
                    templateUrl: "views/edit.html",
                    controller: "createConferenceCtrl"
                }
            }
        })
        .state("conference.list", {
            url: '/list',
            views: {
                "content": {
                    templateUrl: "views/mainspace.html",
                    controller: function ($scope, $http) {
                        $scope.title = "Conferences";
                        $scope.buttons = false;
                        $scope.warName = "Web-1.0-SNAPSHOT";
                        $scope.server = "http://localhost:8080/";
                        $scope.sections = [];
                        var tmp = new Object();
                        tmp.list = [];
                        tmp.title = '';
                        $http.get($scope.server + $scope.warName + "/rest/conference/all")
                            .success(function (data) {
                                angular.forEach(data, function (elem) {
                                    tmp.list.push({
                                        header: elem.name,
                                        link: "conference.info({idconf:"+elem.id+"})",
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
        .state("conference.edit",{
            url:'/edit/{idconf:[0-9]+}',
            views: {
                "content": {
                    templateUrl: "views/edit.html",
                    controller: 'conferenceEditCtrl'
                }
            },
            css: 'css/style.css'
        })
        .state("conference.info", {
            url: '/{idconf:[0-9]+}',
            views: {
                "content": {
                    templateUrl: "views/mainspace.html",
                    controller: 'conferenceCtrl'
                }
            },
            css: ['css/style.css', 'css/all.css']
        })
        .state("conference.speech", {
            url: '/:idconf/speech/{idspeech:[0-9]+}',
            views: {
                "content": {
                    templateUrl: "views/mainspace.html",
                    controller: 'speechCtrl'
                }
            },
            css: ['css/style.css', 'css/all.css']
        })
        .state("conference.room", {
            url: '/:idconf/speech/{idspeech:[0-9]+}/order',
            views: {
                "content": {
                    templateUrl: "views/edit.html",
                    controller: 'roomOrderCtrl'
                }
            },
            css: ['css/style.css', 'css/all.css']
    })
        /*
         .state('conference.speech.question', {
         url: '/speech/{idspeech:[0-9]+}/ask',
         views: {
         'content': {
         templateUrl: 'views/addQuestion.html',
         controller: 'questionCtrl'
         }
         }
         })*/
        .state('conference.question', {
            url: '/speech/:idspeech/question/:idquestion',
            views: {
                "content": {
                    templateUrl: 'views/mainspace.html',
                    controller: 'showquestionCtrl'
                }
            },
            css: ['css/style.css', 'css/all.css']
        })
        .state('conference.askquestion', {
            url: '/speech/:idspeech/ask',
            views: {
                "content": {
                    templateUrl: 'views/addQuestion.html',
                    controller: 'questionCtrl'
                }
            },
            css: ['css/style.css', 'css/all.css']
        })
        .state('conference.translation', {
            url: '/speech/:idspeech/translation',
            views: {
                'content': {
                    templateUrl: 'views/translation.html',
                    controller: 'translationCtrl'
                }
            },
            css: ['css/style.css', 'css/all.css']
        })
        .state('profile', {
            url: '/profile',
            views: {
                "": {
                    templateUrl: 'views/conference.html',
                    controller: function ($http, $log, $scope, $location) {
                        $http.get(remoteServer + '/' + warName + "/rest/whoami")
                            .success(function (data) {
                                $location.path("/profile/" + data.username);
                                $scope.$apply();
                            });
                    }
                }
            },
            css: "css/style.css"
        })
        .state('profile.edit', {
            url: '/edit',
            css: 'css/style.css',
            views: {
                "content": {
                    templateUrl: "views/edit.html",
                    controller: "profileEditCtrl"
                }
            }
        })
        .state('profile.info', {
            url: '/{login}',
            views: {
                "content": {
                    templateUrl: "views/mainspace.html",
                    controller: "profileInfoCtrl"
                }
            },
            css: ['css/style.css', 'css/all.css']
        })
        .state('conference.addspeech', {
            url: '/:idconf/add',
            views: {
                "content": {
                    templateUrl: "views/edit.html",
                    controller: "addSpeechCtrl"
                }
            },
            css: ['css/style.css', 'css/all.css']
        })

        .state('profile.addroom', {
            url: '/room/add',
            views: {
                "content": {
                    templateUrl: "views/edit.html",
                    controller: "addRoomCtrl"
                }
            },
            css: ['css/style.css', 'css/all.css']
        });
});

routerApp.controller('showquestionCtrl', function($scope, $http, $stateParams, $log){
    //$scope.link = "conference.speech({idspeech:square.id})";
    $log.log('id speech ' + $stateParams.idspeech);
    $scope.warName = "Web-1.0-SNAPSHOT";
    $scope.server = "http://localhost:8080/";
    $http.get($scope.server + $scope.warName + "/rest/question/show/" + $stateParams.idquestion)
        .success(function (data) {
            $log.log(data);
            $scope.title = 'Question: ' + data.text;
            if (!data.answer===null)
                $scope.description = 'Answer: ' +data.answer;
            fillPage();
        });
    function fillPage(){
        $scope.buttons = [];
        $scope.sections = [];
        var role ='';
        $http({
            url: remoteServer + '/' + warName + "/rest/whoami",
            method: "GET",

        })
            .success(function (data) {
                role = data.role;
                if (role === 'moderator'){
                    $http({
                        url: remoteServer + '/' + warName + "/rest/modspeech/checkspeech/" + $stateParams.idspeech,
                        method: "GET",
                    }).success(function (data){
                        if (data === true){
                            $http({
                                url: remoteServer + '/' + warName + "/rest/modspeech/checkquestion/" + $stateParams.idquestion,
                                method: "GET",
                            }).success (function (data){
                                if (data === false){
                                    addButtons();
                                }
                            });
                        }
                    });

                    function addButtons(){
                        $scope.buttons = [];
                        var button = new Object();
                        button.text = 'Approve';
                        button.action = function(){
                            $http({
                                url: remoteServer + '/' + warName + "/rest/question/moderate/" + $stateParams.idquestion,
                                method: "GET",
                            }).success(function(data){
                                if (data.result)
                                    $scope.buttons.splice($scope.buttons.indexOf(button),1);
                            });
                        };
                        $scope.buttons.push(button);

                        var button1 = new Object();
                        button1.text = 'Delete';
                        button1.action = function(){
                            $http({
                                url: remoteServer + '/' + warName + "/rest/question/delete",
                                method: "POST",
                                data: "id="+ $stateParams.idquestion,
                                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                            }).success(function(data){
                                if (data.result)
                                    $scope.buttons.splice($scope.buttons.indexOf(button1),1);
                            });
                        };
                        $scope.buttons.push(button1);
                    }
                }
                //addQuestionsAndButtons(role, login, data.username, approved);
            });
    }
});

routerApp.controller('conferenceCtrl', function ($scope, $stateParams, $http, $log, $state) {
    $scope.idconf = $stateParams.idconf;
    $scope.link = "conference.speech({idconf:idconf, idspeech:square.id})";
    $scope.warName = "Web-1.0-SNAPSHOT";
    $scope.server = "http://localhost:8080/";
    $http.get($scope.server + $scope.warName + "/rest/conference/show/" + $stateParams.idconf)
        .success(function (data) {
            $log.log(data);
            $scope.title = data.name;
            $scope.description = data.description;
            fillPage(data.organizer.login);
        });

    function fillPage(login){
        $scope.buttons = [];
        $scope.sections = [];
        var role ='';
        $http({
            url: remoteServer + '/' + warName + "/rest/whoami",
            method: "GET",

        })
            .success(function (data) {
                role = data.role;
                addSpeechesAndButtons(role, login, data.username);
            });
    }



    function addSpeechesAndButtons(value, login,whoami) {
        role = value;
        $log.log('show2 ' + role);

        if (role=='organizer'){
            $http({
                url: $scope.server + $scope.warName + '/rest/conference/uaspeeches/' + $stateParams.idconf,
                method: "GET",
            })
                .success(function (data) {
                    var tmp = new Object();
                    tmp.list = [];
                    tmp.title = '';
                    angular.forEach(data, function (elem) {
                        tmp.list.push({
                            header: elem.topic,
                            link: "conference.speech({idconf: " + $stateParams.idconf + ", idspeech: " + elem.id +"})",
                            text: elem.speaker.name,
                            date: elem.startDate
                        });
                    });
                    if (tmp.list.length !=0)
                        tmp.title = 'Unapproved speeches';
                    $scope.sections.push(tmp);
                    $log.log('login ' + login);
                    $log.log('username ' + whoami);
                    if (whoami == login) {
                        var button = new Object();
                        button.text = 'Edit';
                        button.action = function () {
                            $state.go("conference.edit",{idconf:$stateParams.idconf});
                        };
                        $scope.buttons.push(button);
                    }
                });

        }

        if (role=='speaker') {
            var button = new Object();
            button.text = 'Add Speech';
            button.action = function(){
                $state.go("conference.addspeech", {'idconf': $stateParams.idconf});
            };
            $scope.buttons.push(button);
        }

        if (role=='visitor') {

            $http({
                url: remoteServer + '/' + warName + '/rest/subscribe/check',
                method: "GET",
                params: {visitor: whoami, conf: $stateParams.idconf}
            }).success(function(data){
                if (!data.result){
                    var button = new Object();
                    button.text = 'Register';
                    button.action = function(){
                        $http({
                            url: remoteServer + '/' + warName + '/rest/subscribe/conference',
                            method: "POST",
                            data: "confId=" + $stateParams.idconf,
                            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                        }).success(function(data){
                            if (data.result)
                                $scope.buttons.splice($scope.buttons.indexOf(button),1);
                        });
                    };
                    $scope.buttons.push(button);
                }
            });
        }

            $http({
                url: $scope.server + $scope.warName + '/rest/conference/aspeeches/' + $stateParams.idconf,
                method: "GET",
            })
                .success(function (data) {
                    var tmp = new Object();
                    tmp.list = [];
                    tmp.title = '';
                    angular.forEach(data, function (elem) {
                        tmp.list.push({
                            header: elem.topic,
                            link: "conference.speech({idconf: " + $stateParams.idconf + ", idspeech: " + elem.id +"})",
                            text: elem.speaker.name,
                            date: elem.startDate
                        });
                    });
                    tmp.title = 'Speeches';
                    $scope.sections.push(tmp);
                });
    };
});

routerApp.controller('speechCtrl', function ($scope, $stateParams, $http, $log, $state) {
    $scope.idspeech = $stateParams.idspeech;
    $scope.link = "conference.question({idquestion: square.id, idspeech: idspeech})"
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
            fillPage(data.speaker.login, data.approved);
        });

    function fillPage(login, approved){
        $scope.buttons = [];
        $scope.sections = [];
        var role ='';
        $http({
            url: remoteServer + '/' + warName + "/rest/whoami",
            method: "GET",

        })
            .success(function (data) {
                role = data.role;
                addQuestionsAndButtons(role, login, data.username, approved);
            });
    }

    function addQuestionsAndButtons(value, login,whoami, approved) {
        role = value;
        $log.log('show2 ' + role);

        if (role=='moderator'){
            $http({
                url: remoteServer + '/' + warName + "/rest/modspeech/checkspeech/" + $stateParams.idspeech,
                method: "GET",
            }).success(function (data) {
                if (data === true) {
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
                                    link: "conference.question({idquestion:"+elem.id+", idspeech: "+$stateParams.idspeech+"})",
                                    text: elem.answer
                                });
                            });
                            if (tmp.list.length != 0)
                                tmp.title = 'Unmoderated questions';
                            $scope.sections.push(tmp);
                        });
                }
            });
        }


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
                        link: "conference.question({idquestion: " + elem.id + ", idspeech: " + $stateParams.idspeech +"})",
                        text: elem.answer,
                    });
                });
                tmp.title = 'Questions';
                $scope.sections.push(tmp);
            });

        if (!approved && role=='organizer') {
            var button = new Object();
            button.text = 'Approve';
            button.action = function(){
                $http({
                    url: remoteServer + '/' + warName + "/rest/speech/approve",
                    method: "GET",
                    params: {id: $stateParams.idspeech}
                }).success(function(data){
                    if (data.result)
                        $scope.buttons.splice($scope.buttons.indexOf(button),1);
                });
            };
            $scope.buttons.push(button);
        }

        if (role=='moderator') {
            $http({
                url: $scope.server + $scope.warName + "/rest/speech/"+$stateParams.idspeech+"/modrequested/"+whoami,
                method: "GET"
            }).success(function(data){
                if (!data.result){
                    var button = new Object();
                    button.text = 'Request';
                    button.action = function(){
                        $http({
                            url: remoteServer + '/' + warName + "/rest/modspeech/rmos",
                            method: "GET",
                            params: {speechId: $stateParams.idspeech}
                        }).success(function(data){
                            if (data.result)
                                $scope.buttons.splice($scope.buttons.indexOf(button),1);
                        });
                    };
                    $scope.buttons.push(button);
                }
            });

        }

        if (role=='reporter') {
            $http({
                url: $scope.server + $scope.warName + "/rest/speech/"+$stateParams.idspeech+"/reprequested/"+whoami,
                method: "GET"
            }).success(function(data){
                if (!data.result){
                    var button = new Object();
                    button.text = 'Request';
                    button.action = function(){
                        $http({
                            url: remoteServer + '/' + warName + "/rest/repspeech/rros",
                            method: "GET",
                            params: {speechId: $stateParams.idspeech}
                        });
                    };
                    $scope.buttons.push(button);
                }
            });


        }

        if (role=='speaker' && whoami == login) {
            var button = new Object();
            button.text = 'Edit';
            button.action = function () {
                //TODO
                alert('clicked');
            };
            $scope.buttons.push(button);
        }


        if (role=='visitor') {
            var button = new Object();
            button.text = 'Add question';
            button.action = function(){
                $state.go('conference.askquestion',{ 'idspeech':$stateParams.idspeech });
            };
            $scope.buttons.push(button);

            $http({
                url: remoteServer + '/' + warName + "/rest/subscribe/check",
                method: "GET",
                params: {visitor: whoami , conf: $stateParams.idconf}
            }).success(function(data){
                if (data.result){
                    $http({
                        url: remoteServer + '/' + warName + "/rest/subscribe/check",
                        method: "GET",
                        params: {visitor: whoami , speech: $stateParams.idspeech}
                    }).success(function(data){
                        if (!data.speech){
                            var button = new Object();
                            button.text = 'Register';
                            button.action = function(){
                                $http({
                                    url: remoteServer + '/' + warName + '/rest/subscribe/speech',
                                    method: "POST",
                                    data: "speechId=" + $stateParams.idspeech,
                                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                                }).success(function(data){
                                    if (data.result)
                                        $scope.buttons.splice($scope.buttons.indexOf(button),1);
                                });
                            };
                            $scope.buttons.push(button);
                        }
                    });
                }
            });

        }

        var button = new Object();
        button.text = 'Translation';
        button.action = function(){
            $state.go('conference.translation',{ 'idspeech':$stateParams.idspeech });
        };
        $scope.buttons.push(button);
    }
});

routerApp.controller('translationCtrl', function ($scope, $http, $stateParams) {

    $scope.period = 30;
    $scope.defaultperiod = 30;

    $('#showup').hide();

    $scope.reports = [
        "time",
        "text"
    ];
    $scope.reports = [];
    $scope.speechId = $stateParams.idspeech;
    $scope.lastId = 0;

    $('#reporter').hide();

    $http.get(remoteServer + '/' + warName + '/rest/whoami/')
        .success(function (data) {
            if (data.role=='reporter'){
                $http({
                    url: remoteServer + '/' + warName + '/rest/repspeech/crs',
                    method: "GET",
                    params: {speechId: $scope.speechId, reporterId: data.username}
                }).success(function (response) {
                    if (response)
                        $('#reporter').show();
                });
            }
        });

    $http.get(remoteServer + '/' + warName + '/rest/speech/topic/' + $scope.speechId)
        .success(function (data) {
            $scope.title = data.topic;
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

    $scope.report = function () {
        $http({
            url: remoteServer + '/' + warName + '/rest/trans/insert',
            method: "POST",
            data: "text=" + $scope.text + "&time=" + $scope.time + "&speechId=" + $scope.speechId,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        });
        $scope.text = '';
        $scope.time = '';
        $scope.more();
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
routerApp.controller('profileInfoCtrl', function ($scope, $stateParams, $location,$http,$state) {
    $scope.title = $stateParams.login;
    $scope.buttons = [];
    $scope.sections = [];
    $http.get(remoteServer + '/' + warName + "/rest/whoami")
        .success(function(data) {
            var username = data.username;
            if (username === $stateParams.login) {
                $scope.buttons.push(
                    {
                        text: "Edit Profile",
                        action: function () {
                            $location.path("/profile/edit");
                            $scope.$apply();
                        }
                    }
                );
            }
            $http.get(remoteServer + '/' + warName + "/rest/role/" + $stateParams.login)
                .success(function (data) {
                    if (data.role === "organizer") {
                        $http.get(remoteServer + '/' + warName + "/rest/organizer/show/" + $stateParams.login)
                            .success(function(data){
                                $scope.description = "Organizer, " + data.name + ", " + data.email + ", " + data.phone;
                            });
                        if (username === $stateParams.login) {
                            $scope.buttons.push(
                                {
                                    text: "Create Conference",
                                    action: function () {
                                        $location.path("/conference/create");
                                        $scope.apply();
                                    }
                                }
                            );
                        }
                        $http.get(remoteServer + '/' + warName + "/rest/conference/fcbo/" + $stateParams.login)
                            .success(function (data) {
                                var section = new Object();
                                section.title = "My Conferences";
                                section.list = [];
                                angular.forEach(data, function (elem) {
                                    section.list.push({
                                        header: elem.name,
                                        text: elem.description,
                                        link: "conference.info({idconf:" + elem.id + "})",
                                        date: elem.startDate + " - " + elem.endDate
                                    });
                                    console.log(elem);
                                });
                                $scope.sections.push(section);
                            });
                    }

                    if (data.role === "visitor") {
                        $http.get(remoteServer + '/' + warName + "/rest/visitor/info?login=" + $stateParams.login)
                            .success(function (data) {
                                $scope.description = "Visitor, " + data.name + ", " + data.email + ", " + data.phone;
                            });
                        $http.get(remoteServer + '/' + warName + "/rest/subscribe/conferences/" + $stateParams.username)
                            .success(function (data) {
                                var section = new Object();
                                section.title = "My Conferences";
                                section.list = [];
                                angular.forEach(data, function (elem) {
                                    section.list.push({
                                        header: elem.name,
                                        text: elem.description,
                                        link: "conference.info(" + elem.id + ")",
                                        date: elem.startDate + " - " + elem.endDate
                                    });
                                });
                                $scope.sections.push(section);
                            });
                        $http.get(remoteServer + '/' + warName + "/rest/subscribe/speeches/" + $stateParams.username)
                            .success(function (data) {
                                var section = new Object();
                                section.title = "My Speeches";
                                section.list = [];
                                angular.forEach(data, function (elem) {
                                    section.list.push({
                                        header: elem.topic,
                                        text: elem.speaker.name,
                                        date: elem.startDate,
                                        link: "conference.speech({idspeech:elem.id})"
                                    });
                                });
                                $scope.sections.push(section);
                            });
                    }

                    if (data.role === "reporter") {
                        $http.get(remoteServer + '/' + warName + "/rest/repspeech/fasbr/" + $stateParams.username)
                            .success(function (data) {
                                var section = new Object();
                                section.title = "My Speeches";
                                section.list = [];
                                angular.forEach(data, function (elem) {
                                    section.list.push({
                                        header: elem.topic,
                                        text: elem.speaker.name,
                                        date: elem.startDate,
                                        link: "conference.speech({idspeech:elem.id})"
                                    });
                                });
                                $scope.sections.push(section);
                            });
                    }

                    if (data.role === "speaker") {
                        $http.get(remoteServer + '/' + warName + "/rest/speaker/info?login=" + $stateParams.login)
                            .success(function(data){
                                $scope.description = "Speaker, " + data.name + ", " + data.email + ", " + data.phone;
                            });
                        $http.get(remoteServer + '/' + warName + "/rest/speech/fasbs/" + $stateParams.username)
                            .success(function(data){
                                var section = new Object();
                                section.title="My Speeches";
                                section.list = [];
                                angular.forEach(data, function(elem){
                                    section.list.push({
                                        header:elem.topic,
                                        text:elem.speaker.name,
                                        date:elem.startDate,
                                        link:"conference.speech({idspeech:elem.id})"
                                    });
                                });
                                $scope.sections.push(section);
                            });
                    }

                    if (data.role === "moderator") {
                        $http.get(remoteServer + '/' + warName + "/rest/moderator/show/" + $stateParams.login)
                            .success(function(data){
                                $scope.description = "Moderator, " + data.name + ", " + data.email + ", " + data.phone;
                            });
                        $http.get(remoteServer + '/' + warName + "/rest/modspeech/fasbm/" + $stateParams.username)
                            .success(function(data){
                                var section = new Object();
                                section.title="My Speeches";
                                section.list = [];
                                angular.forEach(data, function(elem){
                                    section.list.push({
                                        header:elem.topic,
                                        text:elem.speaker.name,
                                        date:elem.startDate,
                                        link:"conference.speech({idspeech:elem.id})"
                                    });
                                });
                                $scope.sections.push(section);
                            });
                    }

                    if (data.role === "roomOwner") {
                        $http.get(remoteServer + '/' + warName + "/rest/rowner/get?id=" + $stateParams.login)
                            .success(function(data){
                                $scope.description = "Room Owner, " + data.name + ", " + data.email + ", " + data.phone;
                            });
                        if (username === $stateParams.login) {
                            $scope.buttons.push({
                                text: "Add Room",
                                action: function () {
                                    //$location.path("/room/add");
                                    //$scope.apply();/
                                    $state.go("profile.addroom");
                                }
                            });
                        }
                        $http.get(remoteServer + '/' + warName + "/rest/rowner/" + $stateParams.username)
                            .success(function(data){
                                var section = new Object();
                                section.title = "My Rooms";
                                section.list = [];
                                angular.forEach(data, function(elem){
                                    section.list.push({
                                        header:elem.number,
                                        //description:elem.speaker.name, //TODO:elem.address
                                        link:"room.info({idroom:"+elem.number+"})"
                                    });
                                });
                            });
                    }
                });
        });
});

routerApp.controller('questionCtrl', function($scope, $http, $stateParams) {


    $scope.login = "";
    $scope.speechId = $stateParams.idspeech;
    $scope.server = 'http://localhost:8080/';
    $scope.warName = 'Web-1.0-SNAPSHOT';

    $scope.question = {
        text: "",
        userLogin: "",
        speechId: $scope.speechId//$stateParams.idspeech
    };
    /*
    */

    $http.get($scope.server + $scope.warName + "/rest/whoami")
        .success(function (data) {
            $scope.login = data.login;
        });

    $scope.addQuestion = function () {
        $scope.question.speechId = $scope.speechId;
        alert("add clicked");
        alert( "text=" + $scope.question.text +
            "&speechId=" + $scope.question.speechId);
        $http({
            url: $scope.server + $scope.warName + '/rest/question/add',
            method: "POST",
            data: "text=" + $scope.question.text +
            "&speechId=" + $scope.question.speechId,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data) {
            alert("hello world");
        });

    }
});
routerApp.controller('profileEditCtrl', function($scope,$http,$location,$state){
    $scope.texts = [];
    var getInfo = function(data){
        $scope.texts.push({
            value:data.name,
            placeholder:"Name"
        });
        $scope.texts.push({
            value:data.email,
            placeholder:"E-mail"
        });
        $scope.texts.push({
            value:data.phone,
            placeholder:"Phone"
        })
    };
    $http.get(remoteServer + "/" + warName + "/rest/whoami")
        .success(function(data){
            switch (data.role) {
                case "organizer":
                    $http.get(remoteServer + '/' + warName + "/rest/organizer/show/" + data.username)
                        .success(getInfo);
                    break;
                case "visitor":
                    $http.get(remoteServer + '/' + warName + "/rest/visitor/info?login" + data.username)
                        .success(getInfo);
                    break;
                case "reporter":
                    $http.get(remoteServer + '/' + warName + "/rest/reporter/get/" + data.username)
                        .success(getInfo);
                    break;
                case "moderator":
                    $http.get(remoteServer + '/' + warName + "/rest/moderator/show/" + data.username)
                        .success(getInfo);
                    break;
                case "speaker":
                    $http.get(remoteServer + '/' + warName + "/rest/speaker/info?login" + data.username)
                        .success(getInfo);
                    break;
                case "roomOwner":
                    $http.get(remoteServer + '/' + warName + "/rest/rowner/get?id=" + data.username)
                        .success(getInfo);
                    break;
                default :
                    $location.path("/home");
                    $scope.$apply();
                    break;
            }
            $scope.role = data.role;
            $scope.login = data.username;
        });
    $scope.submit = function() {
        switch ($scope.role) {
            case "organizer":
                $http({
                    url: remoteServer + '/' + warName + '/rest/organizer/update',
                    method: "POST",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    data: 'login='+$scope.login + '&name=' + $scope.texts[0].value + '&email=' + $scope.texts[1].value + '&phone=' + $scope.texts[2].value
                });
                break;
            case "visitor":
                $http({
                    url: remoteServer + '/' + warName + '/rest/visitor/info',
                    method: "POST",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    data: 'login='+$scope.login + '&name=' + $scope.texts[0].value + '&email=' + $scope.texts[1].value + '&phone=' + $scope.texts[2].value
                });
                break;
            case "moderator":
                $http({
                    url: remoteServer + '/' + warName + '/rest/moderator/update',
                    method: "POST",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    data: 'login='+$scope.login + '&name=' + $scope.texts[0].value + '&email=' + $scope.texts[1].value + '&phone=' + $scope.texts[2].value
                });
                break;
            case "speaker":
                $http({
                    url: remoteServer + '/' + warName + '/rest/speaker/info',
                    method: "POST",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    data: 'login='+$scope.login + '&name=' + $scope.texts[0].value + '&email=' + $scope.texts[1].value + '&phone=' + $scope.texts[2].value
                });
                break;
            case "roomOwner":
                $http({
                    url: remoteServer + '/' + warName + '/rest/rowner/update',
                    method: "POST",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    data: 'login='+$scope.login + '&name=' + $scope.texts[0].value + '&email=' + $scope.texts[1].value + '&phone=' + $scope.texts[2].value
                });
                break;
            case "reporter":
                $http({
                    url: remoteServer + '/' + warName + '/rest/reporter/update',
                    method: "POST",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    data: 'login='+$scope.login + '&name=' + $scope.texts[0].value + '&email=' + $scope.texts[1].value + '&phone=' + $scope.texts[2].value + '&busy=false'
                });
                break;
        }
        $state.go("profile.info",{'login':$scope.login});
    }
});

routerApp.controller('addSpeechCtrl', function($scope, $http, $stateParams,$filter) {

    $scope.header = "Add speech";
    $scope.texts = [];
    var dto = new Object();
    dto.placeholder = "Topic";
    $scope.texts.push(dto);

    dto = new Object();
    dto.placeholder = "Text";
    $scope.texts.push(dto);


    $scope.dates = [];
    dto = new Object();
    dto.placeholder = "Starttime";
    $scope.dates.push(dto);


    $scope.submit = function () {

        var post = "topic=" + $scope.texts[0].value +
            "&text=" + $scope.texts[1].value +
            "&start=" + $filter('date')($scope.dates[0].value,"yyyy-MM-dd HH:mm:ss") +
            "&conference=" + $stateParams.idconf;

        alert(post);

        $http({
            url: remoteServer + '/' + warName + '/rest/speech/add',
            method: "POST",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: post
        });



    }
});

routerApp.controller('createConferenceCtrl', function($scope,$http,$location,$filter) {
    $scope.texts = [];
    $scope.dates = [];
    $scope.texts.push({
        placeholder: "Name",
        value: ""
    });
    $scope.texts.push({
        placeholder: "Description",
        value: ""
    });
    $scope.dates.push({
        id:"startdate",
        placeholder:"Start date",
        value: ""
    });
    $scope.dates.push({
        id:"enddate",
        placeholder:"End date",
        value: ""
    });
    $scope.submit = function () {
        $http({
            url: remoteServer + '/' + warName + '/rest/conference/add',
            method: "POST",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: "name=" + $scope.texts[0].value + "&description=" + $scope.texts[1].value + "&start=" + $filter('date')($scope.dates[0].value,"yyyy-MM-dd") + "&end=" + $filter('date')($scope.dates[1].value,"yyyy-MM-dd")
        });
        $location.path("/conference/list");
        $scope.apply();
    };
});
routerApp.controller('conferenceEditCtrl',function($scope,$http,$location,$filter,$stateParams){
    $scope.texts = [];
    $scope.dates = [];
    $scope.texts.push({
        placeholder: "Name",
        value: ""
    });
    $scope.texts.push({
        placeholder: "Description",
        value: ""
    });
    $scope.dates.push({
        id:"startdate",
        placeholder:"Start date",
        value: ""
    });
    $scope.dates.push({
        id:"enddate",
        placeholder:"End date",
        value: ""
    });
    $http.get(remoteServer + "/" + warName + '/rest/whoami')
        .success(function(data1){
            $http.get(remoteServer + "/" + warName + '/rest/conference/show/'+$stateParams.idconf)
                .success(function(data){
                    if (data1.username != data.organizer.login){
                        $location.path('/conference/'+$stateParams.idconf);
                        $scope.apply();
                        return;
                    }
                    $scope.texts[0].value = data.name;
                    $scope.texts[1].value = data.description;
                    $scope.dates[0].value = data.startDate;
                    $scope.dates[1].value = data.endDate;
                });
        })

    $scope.submit = function () {
        $http({
            url: remoteServer + '/' + warName + '/rest/conference/update/' + $stateParams.idconf,
            method: "POST",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: "name=" + $scope.texts[0].value + "&description=" + $scope.texts[1].value + "&start=" + $filter('date')($scope.dates[0].value,"yyyy-MM-dd") + "&end=" + $filter('date')($scope.dates[1].value,"yyyy-MM-dd")
        });
        $location.path("/conference/" + $stateParams.idconf);
        $scope.apply();
    };
})

routerApp.controller('roomOrderCtrl', function($scope, $http, $stateParams) {

    var options = [];

    $http.get(remoteServer + '/' + warName + '/rest/room/all/')
        .success(function (data) {
            angular.forEach(data, function (elem) {
                options.push({value: elem.number, text: elem.number+" ("+elem.capacity+")"});
            });
        });

    var select = new Object();
    select.options=options;

    $scope.selects = [];
    $scope.selects.push(select);

    //$scope.texts = [];
    //var dto = new Object();
    //dto.placeholder = "Topic";
    //$scope.texts.push(dto);
    //
    //dto = new Object();
    //dto.placeholder = "Text";
    //$scope.texts.push(dto);


    $scope.dates = [];

    var dto = new Object();
    dto.placeholder = "Date from";
    $scope.dates.push(dto);

    dto = new Object();
    dto.placeholder = "Date to";
    $scope.dates.push(dto);



    $scope.submit = function () {
        $http({
            url: remoteServer + '/' + warName + '/rest/roomorder/check',
            method: "GET",
            params: {speechId: $stateParams.idspeech, roomId: $scope.selects[0].value,
                dateFrom: $scope.dates[0].value, dateTo: $scope.dates[1].value}
        }).success(function (response) {
            if (!response.result){
                alert("����� �� ��������")
            }else{
                var post = "speechId=" + $stateParams.idspeech +
                    "&roomId=" + $scope.selects[0].value +
                    "&dateFrom=" + $scope.dates[0].value +
                    "&dateTo=" + $scope.dates[1].value;
                alert(post);
                $http({
                    url: remoteServer + '/' + warName + '/rest/roomorder/add',
                    method: "POST",
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                    data: post
                });
            }

        });

    }
});


routerApp.controller('addRoomCtrl', function($scope, $http, $stateParams) {

    $scope.header = "Add room";
    $scope.texts = [];
    var dto = new Object();
    dto.placeholder = "Number";
    $scope.texts.push(dto);

    dto = new Object();
    dto.placeholder = "Capacity";
    $scope.texts.push(dto);



    $scope.submit = function () {

        var post = "number=" + $scope.texts[0].value +
            "&capacity=" + $scope.texts[1].value;
        alert(post);

        $http({
            url: remoteServer + '/' + warName + '/rest/room/add',
            method: "POST",
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            data: post
        });



    }
});
