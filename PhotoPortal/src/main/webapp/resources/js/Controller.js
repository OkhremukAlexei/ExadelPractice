let start = true;

class Controller {
    constructor(posts){
        new Header();
        new Aside();
        new Section();
        Controller.addAll(posts);
    }

    static getRealDate() {
        let date = new Date();
        let day = date.getDate();
        if (day < 10) day = '0' + day;
        let month = date.getMonth() + 1;
        if (month < 10) month = '0' + month;
        let year = date.getFullYear();
        return day + '-' + month + '-' + year;
    }

    static async addAll(photoPosts) {
        if(!!photoPosts)
            View.viewPosts(photoPosts);
        else {
            const posts = await PostService.getPhotoPosts(0, 50);
            View.viewPosts(posts);
        }
    }
}

class User {
    static auth = document.querySelector('#authorization');
    static authForm = User.auth.querySelector('#authorization form');
    static userProfile = document.querySelector('.user-icon');
    static user;
    static photoUser = '';
    constructor() {
        User.authForm.addEventListener('submit', User.setUser);
    }

    static setUser() {
        User.user = document.querySelector('#login').value;
        // User.password = document.querySelector('#password').value;
        User.userProfile.querySelector('span').innerText = User.user;
        if(!!User.user)
            User.userProfile.querySelector('span').innerText = User.user;
        Aside.profileInfo = Aside.profile.querySelector('#profile-info');
        Aside.profilePhotos = Aside.profile.querySelector('#profile-photos');
        Header.UserIcon = document.querySelector('.user-icon');
        Header.UserIcon.addEventListener('click', Header.goToProfile);
        Header.goToHomePage();
        PostService.save();
    }
}

class Post {
    static addPost = document.querySelector('.load-photo');
    static load = document.querySelector('#load-photo');
    static description = document.querySelector('#description');
    static hashtags = document.querySelector('#hashtags');
    static filters = document.querySelectorAll('#filter img');
    static img = document.querySelector('.default-img img');

    constructor() {
        Post.addPost.addEventListener('click', Post.setPost);
        Post.load.addEventListener('change', Post.setPhoto);
        Post.filters.forEach((filter) =>{
            filter.addEventListener('click', Post.setFilter);
        });
    }

    static async setPost() {
        let hashtags = (!!Post.hashtags.value) ? Post.hashtags.value.split(' ') : [];
        let post = {
            id: new Date().getMilliseconds().toString(),
            description: Post.description.value,
            creationDate: Controller.getRealDate(),
            author: User.user,
            photoLink: Post.img.src,
            likes: [],
            hashtags: hashtags,
        };
        if (await PostService.addPhotoPost(post)) {
            View.addPost(post);
            View.addFilter(post, Post.img.classList);
            Post.description.value = '';
            Post.hashtags.value = '';
            Post.img.setAttribute('src', 'resources/images/default-img.png');

            Header.goToHomePage();
            Post.filters.forEach((filter) =>{
                filter.setAttribute('src', 'resources/images/cat.jpg');
            });
            PostService.save();
        }
        else {
            alert("Проверьте введённые данные");
        }
    }

    static setPhoto() {
        Post.img.setAttribute('src', window.URL.createObjectURL(Post.load.files[0]));
        Post.filters.forEach((filter) =>{
            filter.setAttribute('src', window.URL.createObjectURL(Post.load.files[0]));
    });
    }

    static setFilter(event) {
        event.preventDefault();
        Post.img.classList = null;
        Post.img.classList.add(event.target.className);
    }
}

class Header {
    static pageIcon = document.querySelector('.page-icon');
    static title = Header.pageIcon.querySelector('span');
    static UserIcon;
    static logIn = document.querySelector('.log-in');

    constructor() {
        if(!!User.user){
            View.showBlock(User.userProfile);
            User.userProfile.querySelector('span').innerText = User.user;
            Header.UserIcon = document.querySelector('.user-icon');
            Header.UserIcon.addEventListener('click', Header.goToProfile);
        }
        Header.pageIcon.addEventListener('click', Header.goToHomePage);
        Header.logIn.addEventListener('click', Header.goToAuthorization);
    }

    static goToHomePage() {
        Header.title.innerText = 'Лента';
        View.hide(User.auth);
        View.showSection();
        if (User.user) {
            View.hide(Aside.profile);
            View.showBlock(User.userProfile);
            View.hideAdding();
        }
    }

    static goToAuthorization() {
        Header.title.innerText = 'Авторизация';
        View.hideSection();
        if (User.user) {
            View.hide(Aside.profile);
            View.hide(User.userProfile);
            View.hideAdding();
            // if(!!Aside.profilePhotos && !!Aside.profilePhotos)
            //   View.removeProfile(Aside.profilePhotos, Aside.profileInfo);
        }
        if (User.user) User.user = null;
        View.show(User.auth);
        new User();
        PostService.save();
        // User.authForm.removeEventListener('submit', User.setUser);
    }

    static async goToProfile() {
        View.clearContainer(Aside.profile);
        View.viewPhotosProfile(await PostService.getPhotoPosts(0, 10,
            {'author': User.user}), User.user, Aside.profile);
        Header.title.innerText = 'Профиль';
        if (!User.user) Header.goToAuthorization();
        else {
            View.hideAdding();
            View.hideSection();
            View.hide(User.auth);
            View.show(Aside.profile);
            View.hide(User.userProfile);
        }
    }
}

class Aside {
    static profile = document.querySelector('#profile');
    static profileInfo;
    static profilePhotos;
    static addIcon = document.querySelector('.add-post');
    static profileIcon = document.querySelector('.profile');
    static searchingIcon = document.querySelector('.searching');
    static searchingBlock = document.querySelector('#searching');
    static searchByAuthor = document.querySelectorAll('#searching input')[0];
    static searchByDate = document.querySelectorAll('#searching input')[1];
    static search = document.querySelector('#searching form');

    constructor() {
        Aside.addIcon.addEventListener('click', Aside.goToAdding);
        Aside.profileIcon.addEventListener('click', Header.goToProfile);
        Aside.searchingIcon.addEventListener('click', Aside.displaySearching);
        Aside.search.addEventListener('submit', Aside.doSearch);
    }

    static goToAdding() {
        if (User.user) {
            Header.title.innerText = 'Добавить пост';
            View.hide(Aside.profile);
            View.showBlock(User.userProfile);
            View.hideSection();
            View.hide(User.auth);
            View.showAdding();
            new Post();
        }
        else
            Header.goToAuthorization();
    }

    static displaySearching(){
        if(View.isHide(Aside.searchingBlock))
            View.show(Aside.searchingBlock);
        else
            View.hide(Aside.searchingBlock);
    }

    static async doSearch(event) {
        event.preventDefault();
        const filter = Aside.searchByAuthor.value.trim();
        const date = Aside.searchByDate.value;
        const all = await PostService.getPhotoPosts(0, 10);
        let findPosts;
        if(!!Aside.searchByAuthor.value){
            if(filter.charAt[0] === '#'){
                findPosts = await PostService.getPhotoPosts(0, 10, {'hashtags': filter});
                all.forEach((post) => {
                    if (findPosts.findIndex((postFilter) => post.id === postFilter.id) === -1)
                View.removePost(post.id)
            });
            }
            else {
                const findPosts = await PostService.getPhotoPosts(0, 10, {'author': filter});
                all.forEach((post) => {
                    if (findPosts.findIndex((postFilter) => post.id === postFilter.id) === -1)
                View.removePost(post.id)
            });
            }
        }
        else if (!!Aside.searchByDate.value) {
            const findPostsByDate = all.filter((post) => post.creationDate === date);
            findPosts = findPosts || all;
            findPosts.forEach((post) => {
                if (findPostsByDate.findIndex((postFilter) => post.id === postFilter.id) === -1)
            View.removePost(post.id)
        });
        }
    }
}

class Section {
    static posts = document.querySelector('.all-photos');
    static edit = document.querySelector('#edit');
    static editDescription = document.querySelector('#edit-description');
    static editHashtags = document.querySelector('#edit-hashtags');
    static editEnter = Section.edit.querySelector('form');
    static pagination = document.querySelector('.load-photos');
    static currentAmount;

    constructor() {
        Section.currentAmount = 10;
        Section.posts.addEventListener('click', Section.deletePost, false);
        Section.posts.addEventListener('click', Section.editPost, false);
        Section.posts.addEventListener('click', Section.likePost, false);
        Section.posts.addEventListener('click', Section.showByHashtags, false);
        Section.pagination.addEventListener('click', Section.doPagination);
    }

    static async deletePost(event) {
        const elem = event.target;

        if (elem.className === 'delete icon') {
            const path = event.path[1].children[1].childNodes[1];

            if (User.user === path.innerText) {
                if (confirm("Вы точно хотите удалить этот пост?")) {
                    try {
                        await PostService.removePhotoPost(event.path[2].id);
                        View.removePost(event.path[2].id);
                        PostService.save();
                    }catch (e) {
                        alert("Error");
                    }
                }
            }
        }

    }

    static editPost(event) {
        const elem = event.target;


        if (elem.className === 'change icon') {
            const userPath = event.path[1].children[1].childNodes[1];
            if (User.user === userPath.innerText) {
                View.show(Section.edit);
                Section.editEnter.addEventListener('submit', Section.doEdit.bind(null, event.path[2].id,
                    event.path[1].children[2].children[0].children[0], event.path[1].children[2].children[1],
                    event.path[1].children[1].children[1]));
            }
        }
    }

    static async doEdit(id, descriptionTag, hashtagsTag, dateTag) {
        const description = Section.editDescription.value;
        const hashtags = Section.editHashtags.value.split(' ');
        if(await PostService.editPhotoPost(id, {'description': description, 'hashtags': hashtags})) {
            descriptionTag.innerText = description;
            hashtagsTag.innerHTML = hashtags;
            dateTag.innerText = Controller.getRealDate();
            PostService.save();
        }
        View.hide(Section.edit);
    }

    static async likePost(event) {
        const elem = event.target;

        if (elem.className === 'like icon' && !!User.user) {
            const path = event.path[0].children[0];
            const isHasLike = await PostService.changeLike(event.path[2].id, User.user);
            View.noneScaleLike(elem);
            if (isHasLike === false) {
                path.innerText = parseInt(path.innerText) + 1;
                PostService.save();
            } else {
                path.innerText = parseInt(path.innerText) - 1;
                PostService.save();
            }
            View.scaleLike(elem);
        }

    }

    static async showByHashtags(event) {
        const elem = event.target;
        if (elem.tagName === 'A') {
            const filter = event.target.innerText.trim();
            const findPosts = await PostService.getPhotoPosts(0, 10, {'hashtags': filter});
            const all = await PostService.getPhotoPosts(0, 10); // why???
            all.forEach((post) => {
                if (findPosts.findIndex((postFilter) => post.id === postFilter.id) === -1)
                View.removePost(post.id)
            });
        }
    }

    static doPagination() {
        // let posts;
        // if(Controller.testCollection.getAmountPosts() - Section.currentAmount > 10) {
        //     posts = Controller.testCollection.getPhotoPosts(Section.currentAmount, 10);
        //     Section.currentAmount += 10;
        // }
        // else {
        //     posts = Controller.testCollection.getPhotoPosts(Section.currentAmount, Controller.testCollection.getAmountPosts() - Section.currentAmount);
        //     Section.currentAmount += Controller.testCollection.getAmountPosts() - Section.currentAmount;
        // }
        // if(!!posts)
        //     View.viewPosts(posts);
        // if(Controller.testCollection.getAmountPosts() === Section.currentAmount)
        //     View.hide(Section.pagination);
    }
}
if(start) {
    PostService.restore();
    start = false;
}