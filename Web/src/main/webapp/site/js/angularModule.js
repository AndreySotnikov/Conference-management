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
        .state("conference.info",{
            url:'/:idconf',
            views: {
                "content": {
                    templateUrl:"views/mainspace.html",
                    controller: function ($scope, $stateParams) {
                        $scope.title = $stateParams.idconf;
                        $scope.description = $stateParams.idconf;
                        $scope.list = [ {
                            header:"test",
                            text:"test",
                            date:"24.07.2015"
                        },{
                            header:"test2",
                            text:"test2",
                            date:"25.07.2015"
                        },{
                            header:"test3",
                            text:"test3",
                            date:"26.07.2015"
                        },{
                            header:"test4",
                            text:"test4",
                            date:"27.07.2015"
                        }]
                        //buttons - depending on role, include hide
                        //make a REST-call to get all info
                    }
                }
            },
            css:['css/style.css','css/all.css']
        });
});












