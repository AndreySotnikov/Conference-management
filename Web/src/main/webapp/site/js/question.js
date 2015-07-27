/**
 * Created by nikitayakuntsev on 24.07.15.
 */

var app = angular.module('myApp', []);

app.controller('questionCtrl', function($scope, $http) {


    $scope.warName = "Web-1.0-SNAPSHOT";
    $scope.speechId = 2;
    $scope.server = "http://localhost:8080/";

    $scope.login = "";

    $scope.question = {
        text: "",
        userLogin: "",
        speechId: $scope.speechId
    };

    /*
    $http.get($scope.server + $scope.warName + "/rest/whoami")
        .success(function (data) {
            $scope.login = data.login;
            alert(data);
            alert($scope.login)
        });
*/
/*
    $http.get($scope.server + $scope.warName + "/rest/trans/fbs/" + $scope.speechId)
        .success(function (data) {
            angular.forEach(data, function(elem) {
                $scope.reports.push({time:elem[2], text:elem[1]});
            });
            if (data.length>0) {
                $scope.lastId = data[data.length - 1][0];
            }
        });
*/
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
