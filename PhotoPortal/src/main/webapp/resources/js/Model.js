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
            if (!!filterConfig.hashtags) {
                params += '&hashtags=' + filterConfig.hashtags.substring(1, filterConfig.hashtags.length);
            }
        }
        const response = await fetch('/photo-posts' + params, {method: 'GET'});
        return response.json();
    }


    // При отсутствии массива лайков и хэштегов полагаем невалидный пост
    // (если лайков и хэштегов нет, массивы должны быть пустыми)

    static async changeLike(id, user)
    {
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
        let body = JSON.stringify(post);
        const response = await fetch('/photo-post', {headers: headers,
            method: 'POST', body: body});
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return true;
    }

    static async editPhotoPost(id, post) {
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        };
        const data = {
          'id': id,
          'description': post.description,
          'hashtags': post.hashtags
        };
        const param = JSON.stringify(data);
        const response = await fetch('/photo-post/edit', {headers: headers,
            method: 'POST', body: param});
        if (!response.ok) {
            throw new Error(response.statusText);
        }
        return true;
    }

    static async removePhotoPost(id) {
        const response = await fetch('/photo-post?id=' + id, {method: 'DELETE'});
        if (!response.ok) {
            throw new Error(response.statusText);
        }
    }

    static async save() {
        localStorage.removeItem('PostList');
        localStorage.setItem('PostList', JSON.stringify(await PostService.getPhotoPosts(0, 10)));
        if (User.user) localStorage.setItem('userName', User.user);
    }

    static restore() {
        const postList = JSON.parse(localStorage.getItem('PostList'));
        User.user = localStorage.getItem('userName');
        if(JSON.stringify(postList) === "{}")
            new Controller();
        else
            new Controller(postList);
        const jsonPosts = JSON.stringify(PostService.getPhotoPosts(0, 10));
        localStorage.setItem('PostList', jsonPosts);

    }
}
