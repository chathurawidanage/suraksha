/**
 * Created by wik2kassa on 5/27/2016.
 */
'use strict';

var app = angular.module("myApp", ['ngMaterial', 'ngRoute']);

//-------------Routes
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl: 'home.htm',
        controller: 'home-controller'
    }).when("/camp", {
        templateUrl: 'camp.htm',
        controller: 'camp-controller'
    });
}]);
//-------------End of Routes
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
//-------------Services
app.service('hellofy', function () {
    this.alert = function () {
        console.log("hellofy");
    }
});
app.service('locatorService', ['$http', function ($http) {
    this.uploadSearchImage = function (image) {
        var fd = new FormData();
        fd.append('file', image);
        $http.post('/person/search', fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
            .success(function () {
            })
            .error(function () {
            });
    };
    this.postComment = function (personid, commentType, comment) {
        $http.post("/person/" + personid,
            {
                'personId': personid,
                'commentType': commentType,
                'comment': comment
            }
        );
    };
    this.thumbupComment = function () {

    };
    this.editComment = function () {

    }
}]);

app.service('reliefCenterService', ['$http', function ($http) {
    this.addReliefCenter = function () {

    };
    this.getReliefCenters = function () {

    };
}]);
app.service('donationService', ['$http', function ($http) {
    this.makeDonation = function () {

    }
}]);
//-------------End of Services

app.controller('ip-mainmenu-controller', sk_mainmenu_controller);
app.controller('home-controller', sk_home_controller);
app.controller('camp-controller', sk_camp_controller);
//main menu controller
function sk_mainmenu_controller($scope, $mdDialog, $location, hellofy) {
    $scope.reliefCenterCount = 35;
    $scope.donationCount = 400;
    $scope.locatedPeopleCount = 200;
    $scope.open = function (event) {
        $mdDialog.show($mdDialog.alert().title("Open").textContent("Not implemented").ok("Great!").targetEvent(event));
        hellofy.alert();
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

function sk_locator_controller($scope, locator) {
    var image = $scope.image;
    locator.uploadSearchImage(image);
}
function sk_camp_controller($scope, campService) {
    $scope.showPledge = function () {

    }
}

function sk_map_controller($scope,campService) {
    campService.all().then(function(data) {
        data.forEach(function(camp) {
            new google.maps.Marker(
                {
                    position : {
                        lat : camp.location.lat, lng : camp.location.lon
                    },
                    map : map,
                    icon : getMarkerIcon(camp)
                }
            )
            
        });
    });

    function getMarkerIcon(percentage) {
        var icon = {
            url: "img/mapmarker.png", // url
            size: new google.maps.Size(50, 50), // size
            origin: new google.maps.Point(0,0), // origin
            anchor: new google.maps.Point(25,25) // anchor
        };
        return icon;
    }

}