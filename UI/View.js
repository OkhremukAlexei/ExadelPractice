class View {
  static viewPosts(posts) {
    const containerEl = document.querySelector('.all-photos');
    posts.forEach((post) => {
      containerEl.prepend(View._buildPost(post));
    });
  }

  static addUser(user) {
    document.querySelector('header > nav').prepend(View._buildUser(user));
  }

  static addPost(post) {
    document.querySelector('.all-photos').prepend(View._buildPost(post));
  }

  static removePost(id) {
    document.getElementById(id).remove();
  }

  static edit(id, post) {
    const photoPost = document.getElementById(id);
    if (post.photoLink) {
      photoPost.querySelector('img').setAttribute('src', `${post.photoLink}`);
    }
    if (post.hashTags) {
      const tags = photoPost.querySelector('.hashtags');
      tags.innerHTML = `${post.hashTags.map(tag => `<a href="#">${tag} </a>`).join('')}`;
    }
    if (post.description) {
      photoPost.querySelector('h4 > span').innerHTML = `${post.description}`;
    }
  }

  static _buildUser(user) {
    const userEl = document.createElement('div');
    userEl.classList.add('user-icon');
    userEl.innerHTML = `<a href="#" title="Профиль">
                       <img src="dog.jpg"/> 
                       ${user}
                       </a>`;
    return userEl;// ${user.name}, ${user.photo}
  }

  static _buildPost(post) {
    const postEl = document.createElement('article');
    postEl.setAttribute('id', post.id);
    postEl.classList.add('photo-post');
    postEl.innerHTML = `<div class="photo">
            <img src="${post.photoLink}">
            </div>
            <nav class="info">
            <img class="user-icon-info" src="${post.photoLink}"/>
            <div class="user-info">
            <span>${post.author}</span>
            <p>${post.creationDate}</p>
            </div>
            <div class="all-comments comments">
            <h4>${post.author}: <span>${post.description}</span></h4>
            <span class="hashtags"> ${post.hashTags.map(tag => `<a href="#">${tag} </a>`).join('')}
            </span>
            </div>
            <div class="add-comments comments">
            <form class="form-comments">
            <textarea class="area-comments" placeholder="Добавьте комментарий..."></textarea>
            </form>
            </div>
            <button class="delete icon" type="button">
            <img src="garbage.png">
            </button>
            <button class="like icon" type="button">
            <img src="heart.png">
            </button>
            <button class="change icon" type="button">
            <img src="pen.png">
            </button>
            </nav>`;
    return postEl;
  }
}
