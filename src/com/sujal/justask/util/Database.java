package com.sujal.justask.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Database {

    private static final String DATABASE_URI = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "justask";

    private static final String SURVEYS_COLLECTION = "surveys";
    private static final String USERS_COLLECTION = "users";
    private static final String UNREAD_SECTION = "unread";
    private static final String COMPLETED_SECTION = "completed";

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> surveysCollection;
    private MongoCollection<Document> usersCollection;

    public Database() {
        connect();
        initializeCollections();
    }

    private void connect() {
        MongoClientURI uri = new MongoClientURI(DATABASE_URI);
        mongoClient = new MongoClient(uri);
        database = mongoClient.getDatabase(DATABASE_NAME);
    }

    private void initializeCollections() {
        surveysCollection = database.getCollection(SURVEYS_COLLECTION);
        usersCollection = database.getCollection(USERS_COLLECTION);
    }

    public void saveSurvey(String surveyName, Document surveyData) {
        Document survey = new Document("name", surveyName)
                .append("data", surveyData);
        surveysCollection.insertOne(survey);
    }

    public void addUser(String username) {
        Document user = new Document("username", username)
                .append(UNREAD_SECTION, new Document())
                .append(COMPLETED_SECTION, new Document());
        usersCollection.insertOne(user);
    }

    public void addSurveyToUnread(String username, String surveyName, Document surveyData) {
        Document survey = new Document("name", surveyName)
                .append("data", surveyData);
        usersCollection.updateOne(new Document("username", username), new Document("$push", new Document(UNREAD_SECTION, survey)));
    }

    public void moveSurveyToCompleted(String username, String surveyName) {
        usersCollection.updateOne(new Document("username", username), new Document("$pull", new Document(UNREAD_SECTION, new Document("name", surveyName))));
        usersCollection.updateOne(new Document("username", username), new Document("$push", new Document(COMPLETED_SECTION, new Document("name", surveyName))));
    }

    public void printSurveys() {
        for (Document survey : surveysCollection.find()) {
            System.out.println(survey.toJson());
        }
    }

    public void printUsers() {
        for (Document user : usersCollection.find()) {
            System.out.println(user.toJson());
        }
    }
}
