var routerApp = angular.module('app', ['ui.router','door3.css']);

routerApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider
        .state('home', {
            url: '/home',
            views :{
                "" : {
                    templateUrl: 'views/home.html'
                }
            }

        })
        .state('home.login', {
            url: '/login',
            views :{
                "" : {
                    templateUrl: 'views/login.html'
                }
            },
            css: 'css/registration.css'
        })
        .state('home.register', {
            url: '/register',
            views :{
                "" : {
                    templateUrl: 'views/registration.html'
                }
            },
            css: 'css/registration.css',
            controller: function($scope, $http) {
                $scope.master= {};
                $scope.clickBtn = function (user) {
                    $scope.master= angular.copy(user);
                    $http({
                        //url: 'http://127.0.0.1:8080/Web-1.0-SNAPSHOT/rest/register',
                        url: '/Web-1.0-SNAPSHOT/rest/register',
                        method: "POST",
                        //data: { 'login' : user.username,
                        //    'password' : user.password,
                        //    'role' : user.role,
                        //    'phone' : user.phone,
                        //    'email' : user.email,
                        //    'name' : user.name},
                        data: "login="+user.username+
                            "&password="+user.password+
                            "&role="+user.role+
                            "&phone="+user.phone+
                            "&email="+user.email+
                            "&name="+user.name,
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    });
                }

            }
        })
        .state("conference", {
            url:'/conference',
            views :{
                "" : {
                    templateUrl: 'views/conference.html',
                },
            },
            css:"css/style.css",

        })
        .state ("conference.list",{
            url:'/list',
            views:{
                "content":{
                    templateUrl:"views/mainspace.html",
                    controller: function ($scope, $http) {
                        $scope.title = "Conferences";
                        $scope.buttons = false;
                        $scope.warName = "Web-1.0-SNAPSHOT";
                        $scope.server = "http://localhost:8080/";
                        $scope.list = [];
                        $http.get($scope.server + $scope.warName + "/rest/conference/all")
                                .success(function (data) {
                                    angular.forEach(data, function(elem) {
                                        $scope.list.push({
                                            header:elem.name,
                                            id:elem.id,
                                            text:elem.description,
                                            date:elem.startDate + " - " + elem.endDate
                                        });
                                    });
                                });
                    }
                }
            },
            css:['css/style.css','css/all.css']
        })
        .state("conference.info",{
            url:'/{idconf:[0-9]+}',
            views: {
                "content": {
                    templateUrl:"views/mainspace.html",
                    controller: function ($scope, $stateParams, $http, $log) {
                        $scope.warName = "Web-1.0-SNAPSHOT";
                        $scope.server = "http://localhost:8080/";
                        $http.get($scope.server + $scope.warName + "/rest/conference/show/" + $stateParams.idconf)
                            .success(function(data){
                                $log.log(data);
                                $scope.title =data.name;
                                $scope.description = data.description;
                            });
                        $scope.buttons = true;
                        $scope.list = []
                        //buttons - depending on role, include hide all buttons
                        //make a REST-call to get all info
                    }
                }
            },
            css:['css/style.css','css/all.css']
        });
});












