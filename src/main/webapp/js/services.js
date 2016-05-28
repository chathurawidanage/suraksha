/**
 * Created by chathura on 5/28/16.
 */
app.factory('campService', function ($http, $q) {
    return {
        all: function () {
            var defer = $q.defer();
            $http.get("/rest/camp").then(function (response) {
                defer.resolve(response.data);
            }, function (response) {
                defer.reject(response);
            });
            return defer.promise;
        },
        requirements: function (campId) {
            var defer = $q.defer();
            $http.get("/rest/camp/" + campId + "/requirement").then(function (response) {
                defer.resolve(response.data);
            }, function (response) {
                defer.reject(response);
            });
            return defer.promise;
        },
        create: function (campObj) {
            var defer = $q.defer();
            $http.post("/rest/camp/", campObj).then(function (response) {
                defer.resolve(response.data);
            }, function (response) {
                defer.reject(response);
            });
            return defer.promise;
        }
    }
});

app.factory('personService', function ($http, $q) {
    return {
        comment: function (personId, commentStr, commentType) {
            var comment = new Object();
            comment.person = new Object();
            comment.person.id = personId;
            comment.commentType = commentType;
            comment.comment = commentStr;
            var defer = $q.defer();
            $http.post("/rest/person/" + personId + "/comment", JSON.stringify(comment)).then(function (response) {
                defer.resolve(response.data);
            }, function (response) {
                defer.reject(response);
            });
            return defer.promise;
        }
    }
})





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