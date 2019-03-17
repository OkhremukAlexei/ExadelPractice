const photoPosts = [
  {
    id: '1',
    description: 'cats :)',
    creationDate: new Date('2019-01-07T23:59:59'),
    author: 'Alex',
    photoLink: 'http://cats.png',
    likes: ['Vadim'],
    hashTags: ['#funny', 'cats'],
  },

  {
    id: '3',
    description: 'SpaceX',
    creationDate: new Date('2005-02-20T23:00:00'),
    author: 'Alex',
    photoLink: 'http://spaceX.png',
    likes: ['Alex', 'Sidorov'],
    hashTags: ['#space', '#ElonMusk'],
  },

  {
    id: '2',
    description: 'I love Python!!!!!!!',
    creationDate: new Date('2018-02-20T23:00:00'),
    author: 'Dima',
    photoLink: 'https://qph.fs.quoracdn.net/main-qimg-5bcd0509a8c32a59e5d362c1ed531f19',
    likes: ['Vadim', 'Alex'],
    hashTags: [],
  },

  {
    id: '4',
    description: 'ASD!!!! 9/32',
    creationDate: new Date('2019-03-10T12:37:12'),
    author: 'Nekit',
    photoLink: 'https://acm.bsu.by/courses/89/problems/890/1.png',
    likes: ['Alex', 'Vadim', 'Fronter'],
    hashTags: ['#ASD', '#algorithms', 'structure', 'data'],
  },

  {
    id: '5',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '6',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '7',
    description: 'dOgS ))))))))',
    creationDate: new Date('2019-01-20T13:02:29'),
    author: 'Sanya',
    photoLink: 'https://prosobachku.ru/wp-content/uploads/2017/04/harakter-djek-rasel-terier.jpg',
    likes: [],
    hashTags: ['animals', '#dog'],
  },

  {
    id: '8',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '9',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '10',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '11',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '12',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '13',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '14',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '15',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '16',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '17',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '77',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '18',
    description: 'nature',
    creationDate: new Date('2017-05-20T00:00:00'),
    author: 'Natalia',
    photoLink: 'https://s1.1zoom.ru/b5050/41/189587-frederika_1680x1050.jpg',
    likes: ['Alex', 'Aleksandra'],
    hashTags: ['#nature'],
  },

  {
    id: '404',
    description: 'Rick Sanchez',
    creationDate: new Date('2013-12-25T15:04:24'),
    author: 'Pavel',
    photoLink: 'https://www.clipartmax.com/png/middle/194-1947759_rick-sanchez-morty-smith-drawing-deviantart-rick-and-morty-vector.png',
    likes: [],
    hashTags: [],
  },
];

class PostCollection {
  constructor(posts) {
    this._photoPosts = [];
    posts.forEach(post => this.addPhotoPost(post));
  }

  getPhotoPost(id) {
    const realId = Number.parseInt(id, 10);
    if (PostCollection._checkId(realId)) {
      const foundPost = this._photoPosts.filter(post => parseInt(post.id, 10) === realId);
      if (PostCollection._checkObject(foundPost[0])) {
        return foundPost[0];
      }
      return null;
    }
    return null;
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
    if (!this.getPhotoPost(post.id) && PostCollection._validatePhotoPost(post)) {
      this._photoPosts.push(post);
      return true;
    }
    return false;
  }

  editPhotoPost(id, post) {
    if (!this.getPhotoPost(id)) {
      return false;
    }
    if (!PostCollection._checkId(id)) {
      console.log('Incorrect id!');
      return false;
    }
    if (PostCollection._checkObject(post.photoLink)) {
      if (PostCollection._checkString(post.photoLink)
          && !PostCollection.isEmpty(post.photoLink)) {
        this.getPhotoPost(id).photoLink = post.photoLink;
      } else {
        console.log('Incorrect link!');
        return false;
      }
    }

    if (PostCollection._checkObject(post.description)) {
      if (PostCollection._checkString(post.description)
          && !PostCollection.isEmpty(post.description)
          && post.description.length < 200) {
        this.getPhotoPost(id).description = post.description;
      } else {
        console.log('Incorrect description!');
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
    const partsDate = date.split('-');
    const realDate = new Date(date);
    return partsDate[0] === realDate.getFullYear().toString()
            && partsDate[1] - 1 === realDate.getMonth().toString()
            && partsDate[2] === realDate.getDate().toString();
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
const testPost = {
  id: '1',
  description: 'Привет!',
  creationDate: new Date(),
  author: 'Alex',
  photoLink: 'http://hello.by//helloWorld.png',
  likes: [],
  hashTags: [],
};

const test = new PostCollection(photoPosts);
if (!test.getPhotoPost(404)) console.log('Post not found...');
else {
  console.log('Post: ');
  console.log(test.getPhotoPost(404));
}
if (test.addPhotoPost(testPost)) {
  console.log('Post added');
} else {
  console.log('Post invalid');
}
if (PostCollection._validatePhotoPost(
  {
    id: 104, author: 'Dima', photoLink: 'http://F.png', description: 'bla-bla', creationDate: new Date(),
  },
)) {
  console.log('Post valid');
} else {
  console.log('Post invalid');
}
if (test.editPhotoPost(5, { photoLink: 'https://nature.ru//nature.jpg', hashTags: ['#ILoveNature'] })) {
  console.log('Post changed');
} else {
  console.log('Post change failed');
}
if (test.removePhotoPost(3)) {
  console.log('Post deleted');
} else {
  console.log('Post not found...');
}
if (test.getPhotoPosts(0, 20, { hashTags: ['#ASD'] })) {
  console.log(test.getPhotoPosts(0, 20, { hashTags: ['#ASD'] }));
} else {
  console.log('Posts not found...');
}
console.log('All posts: ');
console.log(test.getPhotoPosts(0, 20));
