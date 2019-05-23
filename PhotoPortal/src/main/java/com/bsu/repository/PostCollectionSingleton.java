package com.bsu.repository;

import com.bsu.entity.PhotoPost;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostCollectionSingleton {
    private List<PhotoPost> collection;
    private PostCollectionSingleton() {
        this.collection = new ArrayList<>(Arrays.asList(
                new PhotoPost(1, "Alex", "ahaha :)",
                        "https://i.ytimg.com/vi/_JDnTsb1GfE/maxresdefault.jpg",
                        "20-05-2016", new ArrayList<>(Arrays.asList("Natasha", "Dima")), new ArrayList<>(Arrays.asList("#space", "#Elon"))),
                new PhotoPost(2, "Dima", "SpaceX",
                        "https://media.gq.com/photos/57eac35d9228bbed3f6f4ee5/master/pass/elon-musk-is-a-rocket.jpg",
                        "20-05-2016", new ArrayList<>(Arrays.asList("Natasha", "Dima")), new ArrayList<>(Arrays.asList("#space", "#Elon"))),
                new PhotoPost(3, "Nikita", "SpaceX",
                        "https://media.gq.com/photos/57eac35d9228bbed3f6f4ee5/master/pass/elon-musk-is-a-rocket.jpg",
                        "09-05-2017", new ArrayList<>(Arrays.asList("Natasha", "Dima")), new ArrayList<>(Arrays.asList("#space", "#Elon"))),
                new PhotoPost(4, "Natasha", "SpaceX",
                        "https://media.gq.com/photos/57eac35d9228bbed3f6f4ee5/master/pass/elon-musk-is-a-rocket.jpg",
                        "22-04-2019", new ArrayList<>(Arrays.asList("Natasha", "Dima")), new ArrayList<>(Arrays.asList("#space", "#Elon"))),
                new PhotoPost(5, "Alex", "SpaceX",
                        "https://media.gq.com/photos/57eac35d9228bbed3f6f4ee5/master/pass/elon-musk-is-a-rocket.jpg",
                        "12-03-2019", new ArrayList<>(Arrays.asList("Natasha", "Dima")), new ArrayList<>(Arrays.asList("#space", "#Elon"))),
                new PhotoPost(6, "Dima", "SpaceX",
                        "https://media.gq.com/photos/57eac35d9228bbed3f6f4ee5/master/pass/elon-musk-is-a-rocket.jpg",
                        "22-10-2019", new ArrayList<>(Arrays.asList("Natasha", "Dima")), new ArrayList<>(Arrays.asList("#space", "#Elon"))),
                new PhotoPost(7, "Natasha", "SpaceX",
                        "https://media.gq.com/photos/57eac35d9228bbed3f6f4ee5/master/pass/elon-musk-is-a-rocket.jpg",
                        "22-01-2018", new ArrayList<>(Arrays.asList("Natasha", "Dima")), new ArrayList<>(Arrays.asList("#space", "#Elon"))),
                new PhotoPost(8, "Natasha", "SpaceX",
                        "https://media.gq.com/photos/57eac35d9228bbed3f6f4ee5/master/pass/elon-musk-is-a-rocket.jpg",
                        "25-04-2019", new ArrayList<>(Arrays.asList("Natasha", "Dima")), new ArrayList<>(Arrays.asList("#space", "#Elon"))),
                new PhotoPost(9, "Nikita", "SpaceX",
                        "https://media.gq.com/photos/57eac35d9228bbed3f6f4ee5/master/pass/elon-musk-is-a-rocket.jpg",
                        "22-04-2019", new ArrayList<>(Arrays.asList("Natasha", "Dima")), new ArrayList<>(Arrays.asList("#space", "#Elon"))),
                new PhotoPost(10, "Alex", "SpaceX",
                        "https://media.gq.com/photos/57eac35d9228bbed3f6f4ee5/master/pass/elon-musk-is-a-rocket.jpg",
                        "22-04-2019", new ArrayList<>(Arrays.asList("Natasha", "Dima")), new ArrayList<>(Arrays.asList("#space", "#Elon")))));
    }
    public List<PhotoPost> getCollection(){
        return this.collection;
    }
    public static class PostCollectionHolder {
        public static final PostCollectionSingleton HOLDER_INSTANCE = new PostCollectionSingleton();
    }

    public static PostCollectionSingleton getInstance() {
        return PostCollectionHolder.HOLDER_INSTANCE;
    }

}
