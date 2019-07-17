const photoPosts =  [
    {
        id: '1',
        description: 'ahaha :)',
        creationDate: '2019-01-07',
        author: 'Alex',
        photoLink: 'https://i.ytimg.com/vi/_JDnTsb1GfE/maxresdefault.jpg',
        likes: ['Vadim'],
        hashtags: ['#funny', '#cats'],
    },

    {
        id: '3',
        description: 'SpaceX',
        creationDate: '2019-01-07',
        author: 'Alex',
        photoLink: 'https://media.gq.com/photos/57eac35d9228bbed3f6f4ee5/master/pass/elon-musk-is-a-rocket.jpg',
        likes: ['Alex', 'Sidorov'],
        hashtags: ['#space', '#ElonMusk'],
    },

    {
        id: '2',
        description: 'I love Python!!!!!!!',
        creationDate: '2018-02-20',
        author: 'Dima',
        photoLink: 'https://qph.fs.quoracdn.net/main-qimg-5bcd0509a8c32a59e5d362c1ed531f19',
        likes: ['Vadim', 'Alex'],
        hashtags: [],
    },

    {
        id: '4',
        description: 'ASD!!!! 9/32',
        creationDate: '2019-01-07',
        author: 'Nekit',
        photoLink: 'https://acm.bsu.by/courses/89/problems/890/1.png',
        likes: ['Alex', 'Vadim', 'Fronter'],
        hashtags: ['#ASD', '#algorithms', '#structure', '#data'],
    },
    {
        id: '2134',
        description: 'ASD!!!! 9/32',
        creationDate: '2019-01-07',
        author: 'Nekit',
        photoLink: 'https://acm.bsu.by/courses/89/problems/890/1.png',
        likes: ['Alex', 'Vadim', 'Fronter'],
        hashtags: ['#ASD', '#algorithms', '#structure', '#data'],
    },

    {
        id: 5,
        description: 'nature',
        creationDate: '2017-01-02',
        author: 'Natalia',
        photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
        likes: ['Alex', 'Aleksandra'],
        hashtags: ['#nature'],
    },

    {
        id: '6',
        description: 'nature',
        creationDate: '2017-05-20',
        author: '',
        photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
        likes: ['Alex', 'Aleksandra'],
        hashtags: ['#nature'],
    },

    {
        id: '7',
        description: 'dOgS ))))))))',
        creationDate: '2019-01-20',
        author: 'Sanya',
        photoLink: 'https://prosobachku.ru/wp-content/uploads/2017/04/harakter-djek-rasel-terier.jpg',
        likes: [],
        hashtags: ['#animals', '#dog', '#iloveanimals', '#dogs&cats', '#pets'],
    },

    {
        id: '9',
        description: 'nature',
        creationDate: '2019-01-07',
        author: 'Natalia',
        photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
        likes: [12, 'Aleksandra'],
        hashtags: ['#nature'],
    },

    {
        id: '16',
        description: 'nature',
        creationDate: '2017-04-21',
        author: 'Natalia',
        photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
        likes: ['Alex', 'Aleksandra'],
        hashtags: ['#nature'],
    },

    {
        id: '17',
        description: 'nature',
        creationDate: '2017-05-20',
        author: 'Natalia',
        photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
        likes: ['Alex', 'Aleksandra'],
        hashtags: ['#nature'],
    },

    {
        id: '77',
        description: 'nature',
        creationDate: '2019-01-07',
        author: 'Natalia',
        photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
        likes: ['Alex', 'Aleksandra'],
        hashtags: ['#nature'],
    },

    {
        id: '18',
        description: 'nature',
        creationDate: '2019-01-07',
        author: 'Natalia',
        photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
        likes: ['Alex', 'Aleksandra'],
        hashtags: ['#nature'],
    },

    {
        id: '404',
        description: 'Rick Sanchez',
        creationDate: '2013-12-25',
        author: 'Pavel',
        photoLink: 'https://www.clipartmax.com/png/middle/194-1947759_rick-sanchez-morty-smith-drawing-deviantart-rick-and-morty-vector.png',
        likes: [],
        hashtags: [],
    },
];

class PostService {
    static async getPhotoPost(id) {
        const response = await fetch('/photo-post?id=' + id, {method: 'GET'});
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return response.json();
    }

    static async getPhotoPosts(skip, top, filterConfig) {
        let params = '?skip=' + skip + '&top=' + top;
        if(!!filterConfig) {
            if (!!filterConfig.author) {
                params += '&author=' + filterConfig.author;
            }
            if (!!filterConfig.creationDate) {
                params += '&creationDate=' + filterConfig.creationDate;
            }
        }
        const response = await fetch('/photo-posts' + params, {method: 'GET'});
        return response.json();
    }


    // При отсутствии массива лайков и хэштегов полагаем невалидный пост
    // (если лайков и хэштегов нет, массивы должны быть пустыми)

    static async changeLike(id, user)
    {
        let param = new FormData();
        param.append('id', id);
        param.append('user', user);
        const response = await fetch('/like?id=' + id + '&user=' + user, {method: 'GET'});
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return response.json();
    }

    static async addPhotoPost(post) {
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        };
        const body = JSON.stringify(post);
        const response = await fetch('/photo-post', {headers: headers,
            method: 'POST', body: body});
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return true;
    }

    // editPhotoPost(id, post) {
    //     if (!this.getPhotoPost(id)) {
    //         return false;
    //     }
    //     if (!PostCollection._checkId(id)) {
    //         return false;
    //     }
    //     if (PostCollection._checkObject(post.photoLink)) {
    //         if (PostCollection._checkString(post.photoLink)
    //             && !PostCollection.isEmpty(post.photoLink)) {
    //             this.getPhotoPost(id).photoLink = post.photoLink;
    //         } else {
    //             return false;
    //         }
    //     }
    //
    //     if (PostCollection._checkObject(post.description)) {
    //         if (PostCollection._checkString(post.description)
    //             && !PostCollection.isEmpty(post.description)
    //             && post.description.length < 200) {
    //             this.getPhotoPost(id).description = post.description;
    //         } else {
    //             return false;
    //         }
    //     }
    //     let checkHashTags = false;
    //     if (PostCollection._checkObject(post.hashtags)) {
    //         checkHashTags = post.hashtags.every((hashtag) => {
    //             if (hashtag.charAt(0) !== '#') {
    //             return false;
    //         }
    //         return PostCollection._checkString(hashtag);
    //     }, this);
    //     }
    //     if (checkHashTags) {
    //         this.getPhotoPost(id).hashtags = post.hashtags;
    //         this.getPhotoPost(id).likes = []; // Обнуляем лайки
    //         return true;
    //     }
    //     return false;
    // }

    static async removePhotoPost(post) {
        let param = new FormData();
        param.append('id', post.id);
        let response = await fetch('/photo-post', {method: 'DELETE', body: param});
        if (response.ok) {
            throw new Error(response.statusText);
        }
    }

    static save() {
        localStorage.removeItem('PostList');
        localStorage.setItem('PostList', JSON.stringify(PostService.getPhotoPosts(0, 30)));
        if (User.user) localStorage.setItem('userName', User.user);
    }

    static restore() {
        const postList = JSON.parse(localStorage.getItem('PostList'));
        User.user = localStorage.getItem('userName');
        new Controller();
        const jsonPosts = JSON.stringify(PostService.getPhotoPosts(0, 30));
        localStorage.setItem('PostList', jsonPosts);

    }
}
