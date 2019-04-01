const photoPosts = [
  {
    id: '1',
    description: 'ahaha :)',
    creationDate: '2019-01-07',
    author: 'Alex',
    photoLink: 'https://i.ytimg.com/vi/_JDnTsb1GfE/maxresdefault.jpg',
    likes: ['Vadim'],
    hashTags: ['#funny', '#cats'],
  },

  {
    id: '3',
    description: 'SpaceX',
    creationDate: '2019-01-07',
    author: 'Alex',
    photoLink: 'https://media.gq.com/photos/57eac35d9228bbed3f6f4ee5/master/pass/elon-musk-is-a-rocket.jpg',
    likes: ['Alex', 'Sidorov'],
    hashTags: ['#space', '#ElonMusk'],
  },

  {
    id: '2',
    description: 'I love Python!!!!!!!',
    creationDate: '2018-02-20',
    author: 'Dima',
    photoLink: 'https://qph.fs.quoracdn.net/main-qimg-5bcd0509a8c32a59e5d362c1ed531f19',
    likes: ['Vadim', 'Alex'],
    hashTags: [],
  },

  {
    id: '4',
    description: 'ASD!!!! 9/32',
    creationDate: '2019-01-07',
    author: 'Nekit',
    photoLink: 'https://acm.bsu.by/courses/89/problems/890/1.png',
    likes: ['Alex', 'Vadim', 'Fronter'],
    hashTags: ['#ASD', '#algorithms', 'structure', 'data'],
  },

  {
    id: 5,
    description: 'nature',
    creationDate: '2017-01-02',
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '6',
    description: 'nature',
    creationDate: '2017-05-20',
    author: '',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '7',
    description: 'dOgS ))))))))',
    creationDate: '2019-01-20',
    author: 'Sanya',
    photoLink: 'https://prosobachku.ru/wp-content/uploads/2017/04/harakter-djek-rasel-terier.jpg',
    likes: [],
    hashTags: ['#animals', '#dog', '#iloveanimals', '#dogs&cats', '#pets'],
  },

  {
    id: '9',
    description: 'nature',
    creationDate: '2019-01-07',
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: [12, 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '16',
    description: 'nature',
    creationDate: '2017-04-21',
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '17',
    description: 'nature',
    creationDate: '2017-05-20',
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '77',
    description: 'nature',
    creationDate: '2019-01-07',
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '18',
    description: 'nature',
    creationDate: '2019-01-07',
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '404',
    description: 'Rick Sanchez',
    creationDate: '2013-12-25',
    author: 'Pavel',
    photoLink: 'https://www.clipartmax.com/png/middle/194-1947759_rick-sanchez-morty-smith-drawing-deviantart-rick-and-morty-vector.png',
    likes: [],
    hashTags: [],
  },
];

class PostCollection {
  constructor(posts, user) {
    this._photoPosts = [];
    this._user = user;
    posts.forEach(post => this.addPhotoPost(post));
  }

  getPhotoPost(id) {
    const realId = Number.parseInt(id, 10);
    if (!PostCollection._checkId(realId)) {
      return null;
    }
    const foundPost = this._photoPosts.filter(post => parseInt(post.id, 10) === realId);
    if (!PostCollection._checkObject(foundPost[0])) {
      return null;
    }
    return foundPost[0];
  }

  getPhotoPosts(skip = 0, top = 10, filterConfig = {}) {
    let foundPosts = this._photoPosts.sort((post1, post2) => post1.creationDate - post2.creationDate);
    if (filterConfig) {
      if (Object.prototype.hasOwnProperty.call(filterConfig, 'author')) {
        foundPosts = foundPosts.filter(post => post.author === filterConfig.author);
      } else if (Object.prototype.hasOwnProperty.call(filterConfig, 'hashTags')) {
        if (filterConfig.hashTags.length !== 0) {
          foundPosts = foundPosts.filter((post) => {
            for (let i = 0; i < filterConfig.hashTags.length; i++) {
              for (let j = 0; j < post.hashTags.length; j++) {
                if (post.hashTags[j] === filterConfig.hashTags[i]) {
                  return true;
                }
              }
            }
            return false;
          });
        }
      }
    }
    foundPosts = foundPosts.slice(skip, skip + top);
    if (PostCollection._checkObject(foundPosts) && foundPosts.length !== 0) {
      return foundPosts;
    }

    return null;
  }

  // При отсутствии массива лайков и хэштегов полагаем невалидный пост
  // (если лайков и хэштегов нет, массивы должны быть пустыми)
  static _validatePhotoPost(post) {
    if (PostCollection._checkObject(post) && PostCollection._checkObject(post.id)
            && PostCollection._checkObject(post.description)
            && PostCollection._checkObject(post.creationDate)
            && PostCollection._checkObject(post.author)
            && PostCollection._checkObject(post.photoLink)
            && PostCollection._checkObject(post.likes)
            && PostCollection._checkObject(post.hashTags)) {
      if (PostCollection._checkString(post.id) && PostCollection._checkString(post.description)
                && PostCollection._checkDate(post.creationDate) && post.description.length < 200) {
        if (PostCollection._checkId(post.id) && !this.isEmpty(post.author)
                    && !this.isEmpty(post.photoLink)) {
          if (PostCollection._checkString(post.author)
                 && PostCollection._checkString(post.photoLink)) {
            let checkLikes = false;
            let checkHashTags = false;
            if (post.likes.length !== 0) {
              checkLikes = post.likes.every(author => PostCollection._checkString(author));
            } else {
              checkLikes = true;
            }

            if (post.hashTags.length !== 0) {
              checkHashTags = post.hashTags.every((hashtag) => {
                if (hashtag.charAt(0) !== '#') {
                  return false;
                }
                return PostCollection._checkString(hashtag);
              });
            } else {
              checkHashTags = true;
            }
            return !(!checkLikes || !checkHashTags);
          }
        }
      }
    }
    return false;
  }

  addPhotoPost(post) {
    if (this.getPhotoPost(post.id) || !PostCollection._validatePhotoPost(post)) {
      return false;
    }
    this._photoPosts.push(post);
    return true;
  }

  editPhotoPost(id, post) {
    if (!this.getPhotoPost(id)) {
      return false;
    }
    if (!PostCollection._checkId(id)) {
      return false;
    }
    if (PostCollection._checkObject(post.photoLink)) {
      if (PostCollection._checkString(post.photoLink)
          && !PostCollection.isEmpty(post.photoLink)) {
        this.getPhotoPost(id).photoLink = post.photoLink;
      } else {
        return false;
      }
    }

    if (PostCollection._checkObject(post.description)) {
      if (PostCollection._checkString(post.description)
          && !PostCollection.isEmpty(post.description)
          && post.description.length < 200) {
        this.getPhotoPost(id).description = post.description;
      } else {
        return false;
      }
    }
    let checkHashTags = false;
    if (PostCollection._checkObject(post.hashTags)) {
      checkHashTags = post.hashTags.every((hashtag) => {
        if (hashtag.charAt(0) !== '#') {
          return false;
        }
        return PostCollection._checkString(hashtag);
      }, this);
    }
    if (checkHashTags) {
      this.getPhotoPost(id).hashTags = post.hashTags;
      this.getPhotoPost(id).likes = []; // Обнуляем лайки
      return true;
    }
    return false;
  }

  removePhotoPost(id) {
    const delPost = this.getPhotoPost(id);
    if (delPost) {
      const delIndex = this._photoPosts.indexOf(delPost);
      photoPosts.splice(delIndex, delIndex);
      return true;
    }
    return false;
  }

  addAll(posts) {
    const invalidPosts = [];
    posts.forEach((post) => {
      if (!this.addPhotoPost(post)) invalidPosts.push(post);
    });
    return invalidPosts;
  }

  clear() {
    this._photoPosts = [];
  }

  static _checkString(someString) {
    return typeof someString === 'string';
  }

  static _checkDate(date) {
    if (date instanceof Date) {
      return true;
    }
    const regDate = /^\d{4}-\d{1,2}-\d{1,2}(T[0-2][0-9]:[0-5][0-9]:[0-5][0-9])*$/;
    if (!regDate.test(date.toString())) {
      return false;
    }
    return true;
  }

  static _checkId(id) {
    return /^\d+$/.test(id);
  }

  static _checkObject(post) {
    return !!post;
  }

  static isEmpty(someString) {
    return !someString.trim();
  }
}

const testCollection = new PostCollection([], 'Alex');
