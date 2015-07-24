var routerApp = angular.module('app', ['ui.router','door3.css']);

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
            views :{
                "mainv" : {
                    template: 'views/login.html'
                }
            }
        })
        .state('home.register', {
            url: '/register',
            views :{
                "mainv" : {
                    template: 'views/registration.html'
                }
            },
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
                "mainv" : {
                    template: 'views/conference.html'
                },
                "leftmenu" : {
                    template: 'views/leftmenu.html'
                }
            }


        });
});












