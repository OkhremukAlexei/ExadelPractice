let test = (function(){
    let photoPosts = [
        {
            id: '1',
            description: 'cats :)',
            creationDate: new Date('2019-01-07T23:59:59'),
            author: 'Alex',
            photoLink: 'http://cats.png',
            likes : ["Vadim"],
            hashTags : ["#funny", "cats"]
        },

        {
            id: '3',
            description: 'SpaceX',
            creationDate: new Date('2005-02-20T23:00:00'),
            author: 'Alex',
            photoLink: '',
            likes : ["Alex","Sidorov"],
            hashTags : ["#space","#ElonMusk"]
        },

        {
            id: '2',
            description: 'I love Python!!!!!!!',
            creationDate: new Date('2018-02-20T23:00:00'),
            author: 'Dima',
            photoLink: 'https://qph.fs.quoracdn.net/main-qimg-5bcd0509a8c32a59e5d362c1ed531f19',
            likes : ["Vadim", "Alex"],
            hashTags : []
        },

        {
            id: '4',
            description: 'ASD!!!! 9/32',
            creationDate: new Date('2019-03-10T12:37:12'),
            author: 'Nekit',
            photoLink: 'https://acm.bsu.by/courses/89/problems/890/1.png',
            likes : ["Alex","Vadim", "Fronter"],
            hashTags : ["#ASD","#algorithms","structure", "data"]
        },

        {
            id: '5',
            description: 'nature',
            creationDate: new Date('2017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '6',
            description: 'nature',
            creationDate: new Date('2017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '7',
            description: 'nature',
            creationDate: new Date('2017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '8',
            description: 'nature',
            creationDate: new Date('2017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '9',
            description: 'nature',
            creationDate: new Date('2017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '10',
            description: 'nature',
            creationDate: new Date('2017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '11',
            description: 'nature',
            creationDate: new Date('20017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '12',
            description: 'nature',
            creationDate: new Date('2017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '13',
            description: 'nature',
            creationDate: new Date('2017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '14',
            description: 'nature',
            creationDate: new Date('2017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '15',
            description: 'nature',
            creationDate: new Date('2017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '16',
            description: 'nature',
            creationDate: new Date('2017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '17',
            description: 'nature',
            creationDate: new Date('2017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '77',
            description: 'nature',
            creationDate: new Date('20017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '18',
            description: 'nature',
            creationDate: new Date('2017-05-20T00:00:00'),
            author: 'Natalia',
            photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
            likes : ["Alex", "Aleksandra"],
            hashTags : ["#nature"]
        },

        {
            id: '404',
            description: 'Rick Sanchez',
            creationDate: new Date('2013-12-25T15:04:24'),
            author: 'Pavel',
            photoLink: 'https://www.clipartmax.com/png/middle/194-1947759_rick-sanchez-morty-smith-drawing-deviantart-rick-and-morty-vector.png',
            likes : [],
            hashTags : []
        },
    ];

    return{
        getPhotoPost: function (id) {
            let realId = Number.parseInt(id);
            if (this.checkId(realId)) {
                let foundPost = photoPosts.filter(function (post) {
                    return parseInt(post.id) === realId;
                });
                if(this.checkObject(foundPost[0]))
                {
                    return foundPost[0];
                }
                else{
                    console.log("Post not found...");
                    return null;
                }
            }
        },

        getPhotoPosts: function(skip, top, filterConfig){
            skip = skip || 0;
            top = top || 10;
            let foundPosts = photoPosts.sort(function (post1, post2) {
                return post1.creationDate - post2.creationDate;
            })
            .filter(function (post) {
                    return post.author === filterConfig.author;
                }).slice(skip, skip + top);
            if(this.checkObject(foundPosts)){
                return foundPosts;
            }
            else {
                console.log("Posts not found...");
                return null;
            }
        },

        validatePhotoPost: function(post){
            if(this.checkObject(post) && this.checkObject(post.id) &&
               this.checkObject(post.description) && this.checkObject(post.creationDate) &&
               this.checkObject(post.author) && this.checkObject(post.photoLink) &&
               this.checkObject(post.likes) && this.checkObject(post.hashTags))
            {
                if(this.checkString(post.id) && this.checkString(post.description) &&
                   this.checkDate(post.creationDate) && post.description.length < 200)
                {
                    if(this.checkId(post.id) && this.isEmpty(post.author) &&
                       this.isEmpty(post.photoLink))
                    {
                        if(this.checkString(post.author) && this.checkString(post.photoLink)){
                            let checkLikes = false;
                            let checkHashTags = false;
                            if(post.likes.length !== 0) {
                                checkLikes = post.likes.every(function (author) {
                                    return this.checkString(author);
                                }, this);
                            }
                            else {
                                checkLikes = true;
                            }

                            if(post.hashTags.length !== 0){
                                checkHashTags = post.hashTags.every(function (hashtag) {
                                    if(hashtag.charAt(0) !== '#'){
                                        return false;
                                    }
                                    return this.checkString(hashtag);
                                }, this);
                            }
                            else {
                                checkHashTags = true;
                            }

                            return !(!checkLikes || !checkHashTags);
                        }
                    }
                }
            }
            return false;
        },

        addPhotoPost: function(post){
            if(this.validatePhotoPost(post)){
                photoPosts.push(post);
                return true;
            }
            else {
                return false;
            }

        },

        // editPhotoPost: function(id, post){
        //
        // }

        checkString: function(someString){
            return typeof someString === "string";
        },

        checkDate: function(date){
            if(date instanceof Date){
                return true;
            }
            let regDate = /^\d{4}-\d{1,2}-\d{1,2}(T[0-2][0-9]:[0-5][0-9]:[0-5][0-9])*$/;
            if(!regDate.test(date.toString())){
                return false;
            }
            let partsDate = date.split("-");
            let realDate = new Date(date);
            return partsDate[0] === realDate.getFullYear().toString() &&
                   partsDate[1] - 1 === realDate.getMonth().toString() &&
                   partsDate[2] === realDate.getDate().toString();
        },

        checkId: function(id)
        {
            return /^\d+$/.test(id);
        },

        checkObject: function(post)
        {
            return !!post;
        },

        isEmpty: function (someString) {
            return someString.trim();
        }
    }
}());
let testPost = {
        id: '103',
        description: 'Привет!',
        creationDate: new Date('2005-02-20T23:00:00'),
        author: 'Alex',
        photoLink: 'http://hello.by//helloWorld.png',
        likes : [],
        hashTags : []
};
console.log(test.getPhotoPost(404));
console.log(test.getPhotoPosts(0, 19, {author: "Alex"}));
console.log(test.validatePhotoPost({id: 3, author: "CoolDog", photoLink: "http://vk.com//logo.jpg"}));
console.log(test.addPhotoPost(testPost));
