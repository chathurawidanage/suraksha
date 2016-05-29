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
        },
        addRequirement: function(campid, req) {
            //{"id":3,"name":"Paracetamol","description":"Panadol","required":150,"pledged":100,"recieved":20,
            // "type":2,"$$hashKey":"object:26","offer":16}]
            var data = {
                id : req.id,
                name : req.name,
                description : req.description,
                required : req.required,
                pledged : req.pledged + req.offer,
                recieved : req.recieved,
                type : req.type
            };

            console.log('pushing req data: ' + JSON.stringify(data));
            var defer = $q.defer();
            $http.post("/rest/camp/" + campid + "/requirement/", data).then(function (response) {
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
    this.results=[];
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