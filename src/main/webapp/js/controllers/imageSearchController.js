/**
 *@author Chathura Widanage
 */
app.controller('imageSearchController', function ($scope, locatorService, $http, $location, $rootScope) {
    var ctrl = this;
    ctrl.file;
    $scope.$parent.globalCtrl.setNavTitle("Locate a Person");

    ctrl.loading = false;

    $scope.dropzoneConfig = {
        'options': { // passed into the Dropzone constructor
            'url': '/rest/person/match',
            'uploadMultiple': false,
            'acceptedFiles': 'image/*',
            'clickable': false
        },
        'eventHandlers': {
            'sending': function (file, xhr, formData) {
                console.log("sending");
                ctrl.loading = true;
            },
            'success': function (file, response) {
                locatorService.result = response;
                console.log(response);
                $rootScope.$apply(function () {
                    $location.path("/find-res");
                    console.log($location.path());
                });
            }
        }
    };

    $scope.uploadFile = function (files) {
        var fd = new FormData();
        //Take the first selected file
        fd.append("file", files[0]);

        $http.post("/rest/person/match", fd, {
            withCredentials: true,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).success(function (data) {
            console.log(data);
        }).error();

    };
});

app.controller("imageResultController", function ($scope, locatorService) {
    var ctrl = this;
    ctrl.hasResult = locatorService.result.length > 0;
    ctrl.phone="";
    console.log(locatorService.result);
    $scope.$parent.globalCtrl.setNavTitle("Search Results");
    if (ctrl.hasResult) {
        ctrl.person = locatorService.result[0];
        console.log(ctrl.person);
    }

    ctrl.getImageUrl = function () {
        return "images/" + ctrl.person.image;
    }
})
