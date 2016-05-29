/**
 *@author Chathura Widanage
 */
app.controller('imageSearchController', function ($scope, locatorService, $http) {
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
