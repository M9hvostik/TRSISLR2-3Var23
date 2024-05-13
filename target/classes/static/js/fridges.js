var app = angular.module('fridges', []);

app.controller("FridgesController", function ($scope, $http) {

    $scope.successGetFridgesCallback = function (response) {                
         $scope.fridgesList = response.data;
        $scope.errormessage="";
    };

    $scope.errorGetFridgesCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get list of fridges";
    };

    $scope.getFridges = function () {
        $http.get('/public/rest/fridges').then($scope.successGetFridgesCallback, $scope.errorGetFridgesCallback);
    };

    $scope.successDeleteFridgesCallback = function (response) {
        for (var i = 0; i < $scope.fridgesList.length; i++) {
            var j = $scope.fridgesList[i];
            if (j.id === $scope.deletedId) {
                $scope.fridgesList.splice(i, 1);
                break;
            }
        }
        $scope.errormessage="";
    };

    $scope.errorDeleteFridgeCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to delete fridge; check if there are any related records exist. Such records should be removed first";
    };

    $scope.deleteFridge = function (id) {
        $scope.deletedId = id;
        $http.delete('/public/rest/fridges/' + id).then($scope.errorDeleteFridgeCallback, $scope.errorDeleteFridgeCallback);
    };


    $scope.successGetFridgeCallback = function (response) {
        $scope.fridgesList.splice(0, 0, response.data);
        $scope.errormessage="";
    };

    $scope.errorGetFridgeCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get information on fridge number "+$scope.inputnumber;
    };

    $scope.successAddFridgeCallback = function (response) {
        $http.get('/public/rest/schools/' + $scope.inputnumber).then($scope.successGetSchoolCallback, $scope.errorGetSchoolCallback);
        $scope.errormessage="";
    };

    $scope.errorAddFridgeCallback = function (error) {
        console.log(error);
        $scope.errormessage="Impossible to add new fridge; check if it's number is unique";
    };

    $scope.addFridge = function () {
        $http.post('/public/rest/fridges/' + $scope.inputnumber + "/" + $scope.inputname).then($scope.successAddFridgeCallback, $scope.errorAddFridgeCallback);
    };

});
