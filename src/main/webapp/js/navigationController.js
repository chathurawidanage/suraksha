/**
 * Created by Lasantha on 28-May-16.
 */

app.controller('globalController', function ($scope, $rootScope, $timeout, $mdSidenav, $log, campService) {
    $scope.globalCtrl = this;
    $scope.sideNavLeftOpened = 'slide_back';
    $scope.toggleLeft = buildToggler('left');
    $scope.isOpenLeft = function () {
        return $mdSidenav('left').isOpen();
    };

    var me = this;

    this.title = "Title";

    this.setNavTitle = function (title) {
        me.title = title;

    };
    this.openSlide = function() {
        $scope.sideNavLeftOpened = 'slide_left';
        $mdSidenav('left').open()
            .then(function () {
                $log.debug("open RIGHT is done");
            });
    };

    this.closeSlide = function() {
        $scope.sideNavLeftOpened = 'slide_back';
        console.log("closing slide...");
        $mdSidenav('left').close()
            .then(function () {
                $log.debug("close RIGHT is done");
            });
    }
    $scope.close = function () {
        $scope.sideNavLeftOpened = 'slide_back';
        $mdSidenav('left').close()
            .then(function () {
                $log.debug("close RIGHT is done");
            });
    };

    $scope.open = function () {
        console.log("openign nav");
        $scope.sideNavLeftOpened = 'slide_left';
        $mdSidenav('left').open()
            .then(function () {
                $log.debug("open RIGHT is done");
            });
    };

    function buildToggler(navID) {
        return function () {
            if ($scope.sideNavLeftOpened == 'slide_back') {
                $scope.sideNavLeftOpened = 'slide_left';
            } else {
                $scope.sideNavLeftOpened = 'slide_back';
            }
            $mdSidenav(navID)
                .toggle()
                .then(function () {
                    $log.debug("toggle " + navID + " is done");
                });
        }
    }

    /**
     * Supplies a function that will continue to operate until the
     * time is up.
     */
    function debounce(func, wait, context) {
        var timer;
        return function debounced() {
            var context = $scope,
                args = Array.prototype.slice.call(arguments);
            $timeout.cancel(timer);
            timer = $timeout(function () {
                timer = undefined;
                func.apply(context, args);
            }, wait || 10);
        };
    }
});
