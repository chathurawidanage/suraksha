/**
 *@author Chathura Widanage
 */
app.controller('imageSearchController', function ($scope, locatorService) {
    var ctrl = this;
    ctrl.file;
    $scope.$parent.globalCtrl.setNavTitle("Find Person");
    
    ctrl.openFileBrowser=function () {
        angular.element(document.querySelector('#fileInput')).click();
    }
});
