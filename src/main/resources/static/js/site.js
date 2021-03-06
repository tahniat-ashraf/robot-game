var app = new Vue({
    el: '#app',
    data: {
        originalCommands: 'POSITION 1 1 NORTH\n' +
            'FORWARD 1\n' +
            'WAIT\n' +
            'LEFT\n' +
            'FORWARD 1\n' +
            'TURNAROUND',
        commands: 'POSITION 1 1 NORTH\n' +
            'FORWARD 1\n' +
            'WAIT\n' +
            'LEFT\n' +
            'FORWARD 1\n' +
            'TURNAROUND',
        north: 'fab fa-android',
        east: 'fab fa-android fa-rotate-90',
        south: 'fab fa-android fa-rotate-180',
        west: 'fab fa-android fa-rotate-270',
        errorMessageVisibility: 'none',
        errorMessage: 'Invalid instruction',
        targetId: '',

    },
    methods: {
        getClassByDirection: function (direction) {

            if (direction === 'NORTH') {
                return this.north;
            } else if (direction === 'SOUTH') {
                return this.south;
            } else if (direction === 'EAST') {
                return this.east;
            } else {
                return this.west;
            }

        },
        play: function (event) {

            $(this.targetId).removeClass();
            this.errorMessageVisibility = 'none';
            const url = window.location.href + "move";

            axios.post(url,
                {
                    "commands": this.commands
                })
                .then(response => {
                    console.log(response);
                    var xVal = response.data.position.xaxisPosition;
                    var yVal = response.data.position.yaxisPosition;
                    var classVal = this.getClassByDirection(response.data.position.direction);
                    this.targetId = "#" + xVal + "" + yVal;
                    console.log("targetId " + this.targetId);
                    $(this.targetId).addClass(classVal);

                })
                .catch(error => {
                    console.error(error.response.data);
                    this.errorMessageVisibility = 'block';
                    this.errorMessage = error.response.data.errorMessage;
                })
        }
    }
})