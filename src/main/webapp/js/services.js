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
            $http.get("/rest/camp/" + campId+"/requirement").then(function (response) {
                defer.resolve(response.data);
            }, function (response) {
                defer.reject(response);
            });
            return defer.promise;
        }
    }
});

app.factory('requirementService', function ($http, $q) {
    return {}
})