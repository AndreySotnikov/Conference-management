var routerApp = angular.module('app', ['ui.router','door3.css']);
var remoteServer = 'http://127.0.0.1:8080';
//var remoteServer = '';
var warName='Web-1.0-SNAPSHOT';
routerApp.config(function($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});
routerApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider
        .state('home', {
            url: '/home',
            views :{
                "mainv" : {
                    templateUrl: 'views/home.html',
                    css: 'css/registration.css'
                }
            }
        })
        .state('home.login', {
            url: '/login',
            views :{
                "mainv" : {
                    templateUrl: 'views/login.html',
                    css: 'css/registration.css'
                }
            },
            controller: function($scope, $http) {
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
                }
            }
        })
        .state('home.register', {
            url: '/register',
            views :{
                "mainv" : {
                    templateUrl: 'views/registration.html',
                    css: 'css/registration.css'
                }
            },
            controller: function($scope, $http) {
                $scope.master= {};
                $scope.clickBtn = function (user) {
                    $scope.master = angular.copy(user);
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
                }

            }
        })
        .state("conference", {
            url:'/conference',
            css: ['css/style.css','css/all.css'],
            views :{
                'mainv' : {
                    templateUrl: 'views/conference.html'
                },
                'leftmenu@conference' : {
                    templateUrl: 'views/leftmenu.html'
                }
            }


        })
        .state('translation', {
            url: '/translation',
            views :{
                'mainv' : {
                    templateUrl: 'views/translation.html',
                    controller: 'translationCtrl'
                }
            },

        });
});
routerApp.controller('translationCtrl', function($scope, $http) {

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
    $http.get(remoteServer +'/' +warName+ '/rest/speech/topic/' + $scope.speechId)
        .success(function (data) {
            $scope.title = data;
        });
    $http.get(remoteServer +'/' +warName+ '/rest/trans/fbs/' + $scope.speechId)
        .success(function (data) {
            angular.forEach(data, function(elem) {
                $scope.reports.push({time:elem[2], text:elem[1]});
            });
            if (data.length>0) {
                $scope.lastId = data[data.length - 1][0];
            }
        });

    $scope.autoUpdates = function() {
        if ($scope.autoUpdate){
            $scope.more();
            if ($scope.myForm.$valid){
                setTimeout($scope.autoUpdates, 1000*$scope.period);
            }else{
                setTimeout($scope.autoUpdates, 1000*$scope.defaultperiod);
            }

        }
    }

    $scope.more = function() {
        $http({
            url: remoteServer +'/' +warName+ '/rest/trans/updates',
            method: "GET",
            params: {speechId: $scope.speechId, lastId: $scope.lastId}
        }).success(function (data) {
            angular.forEach(data, function(elem) {
                $scope.reports.push({time:elem[2], text:elem[1]});
            });
            if (data.length>0) {
                $scope.lastId = data[data.length - 1][0];
            }
        });
    }

    $scope.changeCheckbox = function() {
        if ($scope.autoUpdate){
            $('#showup').show();
            $scope.autoUpdates();
        }else{
            $('#showup').hide();
        }
    }
});
/*var routerApp = angular.module('app', ['ui.router','door3.css']);

var remoteServer = 'http://127.0.0.1:8080';
//var remoteServer = '';
var warName='Web-1.0-SNAPSHOT';
routerApp.config(function($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});
routerApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider
        .state('home', {
            url: '/home',
            views :{
                "mainv" : {
                    template: 'views/home.html'
                }
            }

        })
        .state('home.login', {
            url: '/login',
            templateUrl: 'views/login.html',
            css: 'css/registration.css',
            controller: function($scope, $http) {
                $scope.master = {};
                $scope.clickBtn = function (user) {
                    $scope.master = angular.copy(user);
                    $http({
                        url: remoteServer+'/'+warName+'/rest/j_security_check',
                        method: "POST",
                        data: "j_username=" + user.j_username +
                        "&j_password=" + user.j_password,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    });
            views :{
                "mainv" : {
                    template: 'views/login.html'
                }
            }
        })
        .state('home.register', {
            url: '/register',
            templateUrl: 'views/registration.html',
            css: 'css/registration.css',
            controller: function($scope, $http, $state) {
                views
        :
            {
                "mainv"
            :
                {
                    template: 'views/registration.html'
                }
            }
        ,
            controller: function ($scope, $http) {
                $scope.master = {};
                $scope.clickBtn = function (user) {
                    $scope.master = angular.copy(user);
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
        })
        .state('translation', {
            url: '/translation',
            templateUrl: 'views/translation.html',
            controller: 'translationCtrl'
        })
        .state("conference", {
            url:'/conference',
            views :{
                "mainv" : {
                    template: 'views/conference.html'
                },
                "leftmenu" : {
                    template: 'views/leftmenu.html'
                }
            }
        });
routerApp.controller('translationCtrl', function($scope, $http) {

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
    $http.get(remoteServer +'/' +warName+ '/rest/speech/topic/' + $scope.speechId)
        .success(function (data) {
            $scope.title = data;
        });
    $http.get(remoteServer +'/' +warName+ '/rest/trans/fbs/' + $scope.speechId)
        .success(function (data) {
            angular.forEach(data, function(elem) {
                $scope.reports.push({time:elem[2], text:elem[1]});
            });
            if (data.length>0) {
                $scope.lastId = data[data.length - 1][0];
            }
        });

    $scope.autoUpdates = function() {
        if ($scope.autoUpdate){
            $scope.more();
            if ($scope.myForm.$valid){
                setTimeout($scope.autoUpdates, 1000*$scope.period);
            }else{
                setTimeout($scope.autoUpdates, 1000*$scope.defaultperiod);
            }

        }
    }

    $scope.more = function() {
        $http({
            url: remoteServer +'/' +warName+ '/rest/trans/updates',
            method: "GET",
            params: {speechId: $scope.speechId, lastId: $scope.lastId}
        }).success(function (data) {
            angular.forEach(data, function(elem) {
                $scope.reports.push({time:elem[2], text:elem[1]});
            });
            if (data.length>0) {
                $scope.lastId = data[data.length - 1][0];
            }
        });
    }

    $scope.changeCheckbox = function() {
        if ($scope.autoUpdate){
            $('#showup').show();
            $scope.autoUpdates();
        }else{
            $('#showup').hide();
        }
    }
});


*/








