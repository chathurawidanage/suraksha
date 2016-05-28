/**
 * @author Yasiru Kassapa
 */
'use strict';

var app = angular.module("myApp", ['ngMaterial', 'ngRoute']);

//-------------Routes
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: 'templates/empty.html',
            controller: 'home-controller'
        }).when("/pledge", {
        templateUrl: 'templates/pledge.html',
        controller: 'pledgeController'
    }).when("/find",{
        templateUrl:'templates/imagesearch.html',
        controller: 'imageSearchController'
    });
}]);
//-------------End of Routes
//-------------Themes
app.config(function($mdThemingProvider) {
    $mdThemingProvider.theme('default')
        .primaryPalette('teal')
        .accentPalette('orange');
})
//-------------End of Themes
//-------------Directives
app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
//-------------End of Directives

app.controller('ip-mainmenu-controller', sk_mainmenu_controller);
app.controller('home-controller', sk_home_controller);
app.controller('camp-controller', sk_camp_controller);
app.controller('blank-controller', sk_blank_controller);
app.controller('imageSearchController', function (locatorService) {
    var ctrl = this;
    ctrl.file;
});

//main menu controller
function sk_mainmenu_controller($scope, $mdDialog, $location) {
    $scope.reliefCenterCount = 35;
    $scope.donationCount = 400;
    $scope.locatedPeopleCount = 200;
    $scope.open = function (event) {
        $mdDialog.show($mdDialog.alert().title("Open").textContent("Not implemented").ok("Great!").targetEvent(event));
    }
    $scope.loadView = function (view) {
        $location.path(view);
    }
}

function sk_home_controller($scope, $location) {
    $scope.loadView = function (view) {
        console.log("buttons");
        $location.path(view);
    };
}
function sk_blank_controller($scope) {

}
function sk_locator_controller($scope, locator) {
    var image = $scope.image;
    locator.uploadSearchImage(image);
}
function sk_camp_controller($scope, campService) {
    $scope.showPledge = function () {

    }
}

