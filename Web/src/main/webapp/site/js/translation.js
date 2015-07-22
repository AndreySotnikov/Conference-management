
var app = angular.module('myApp', []);

app.controller('translationCtrl', function($scope, $http) {

    $scope.reports = [
        "time",
        "text"
    ];
    $scope.reports = [];

    $scope.warName = "Web-1.0-SNAPSHOT";
    $scope.speechId = 2;
    $scope.lastId = 0;
    $scope.server = "http://localhost:8080/";                        // Достаем заголовок доклада
    $http.get($scope.server + $scope.warName + "/rest/speech/topic/" + $scope.speechId)
        .success(function (data) {
            $scope.title = data;
        });
    // Достаем все сообщения трансляции
    $http.get($scope.server + $scope.warName + "/rest/trans/fbs/" + $scope.speechId)
        .success(function (data) {
            angular.forEach(data, function(elem) {
                $scope.reports.push({time:elem[2], text:elem[1]});
            });
            if (data.length>0) {
                $scope.lastId = data[data.length - 1][0];
            }
            $scope.autoUpdates();
        });

    $scope.autoUpdates = function() {
        $scope.more();
        setTimeout($scope.autoUpdates, 30000);
    }

    // Получаем обновления
    $scope.more = function() {
        $http({
            url: $scope.server + $scope.warName + "/rest/trans/updates",
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
});
