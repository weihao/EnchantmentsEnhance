Vue.component("table-grid", {
    template: "#grid-template",
    props: {
        data: Array,
        columns: Array,
        filterKey: String
    },
    data: function () {
        var sortOrders = {};
        this.columns.forEach(function (key) {
            sortOrders[key] = 1;
        });
        return {
            sortKey: "",
            sortOrders: sortOrders
        };
    },
    computed: {
        filteredData: function () {
            var sortKey = this.sortKey;
            var filterKey = this.filterKey && this.filterKey.toLowerCase();
            var order = this.sortOrders[sortKey] || 1;
            var data = this.data;
            if (filterKey) {
                data = data.filter(function (row) {
                    return Object.keys(row).some(function (key) {
                        return (
                            String(row[key])
                                .toLowerCase()
                                .indexOf(filterKey) > -1
                        );
                    });
                });
            }
            if (sortKey) {
                data = data.slice().sort(function (a, b) {
                    a = a[sortKey];
                    b = b[sortKey];
                    return (a === b ? 0 : a > b ? 1 : -1) * order;
                });
            }
            return data;
        }
    },
    filters: {
        capitalize: function (str) {
            return str.charAt(0).toUpperCase() + str.slice(1);
        }
    },
    methods: {
        sortBy: function (key) {
            this.sortKey = key;
            this.sortOrders[key] = this.sortOrders[key] * -1;
        }
    }
});

var table = new Vue({
    el: "#table",
    data: {
        searchQuery: "",
        gridColumns: ["", "premium", "free"],
        gridData: [
            {
                "": "Update",
                premium: "Automated continuous integration and delivery ready",
                free: "3 weeks development time behind the premium version"
            },
            {"": "Report A Bug", premium: "✔", free: "✔"},
            {"": "Request Features", premium: "✔", free: "✔"},
            {"": "Configuration Support", premium: "✔", free: "-"},
            {"": "Prioritized Help", premium: "✔", free: "-"},
            {"": "Make a wild developer happy", premium: "✔", free: "-"}
        ]
    }
});
