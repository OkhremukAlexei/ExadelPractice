const testPage = (function () {
  return {
    setUser() {
      View.addUser(testCollection._user);
    },
    addPost(post) {
      if (testCollection.addPhotoPost(post)) {
        View.addPost(post);
      }
    },
    removePost(id) {
      if (testCollection.removePhotoPost(id)) {
        View.removePost(id);
      }
    },
    edit(id, newPost) {
      if (testCollection.editPhotoPost(id, newPost)) {
        View.edit(id, newPost);
      }
    },
    addAll(posts) {
      const invalidPosts = testCollection.addAll(posts);
      View.viewPosts(testCollection.getPhotoPosts(0, posts.length - invalidPosts.length));
    },
  };
}());
const testPost = {
  id: '3222',
  description: 'ASD!!!! 9/32',
  creationDate: '2019-03-06',
  author: 'Nekit',
  photoLink: 'https://acm.bsu.by/courses/89/problems/890/1.png',
  likes: ['Alex', 'Vadim', 'Fronter'],
  hashTags: ['#ASD', '#algorithms', '#structure', '#data'],
};

testPage.addAll(photoPosts);
testPage.setUser();
testPage.addPost(testPost);
testPage.removePost(77);
testPage.removePost(18);
testPage.edit(3222, { description: '25/47!!!', photoLink: 'https://acm.bsu.by/courses/89/problems/3560/pic.png', hashTags: ['#graphs'] });
