var routerApp = angular.module('app', ['ui.router','door3.css']);

routerApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider
        .state('home', {
            url: '/home',
            templateUrl: 'views/home.html'
        })
        .state('home.login', {
            url: '/login',
            templateUrl: 'views/login.html',
        })
        .state('home.register', {
            url: '/register',
            templateUrl: 'views/registration.html',
            controller: function($scope) {

            }
        });
});












