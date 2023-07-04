package com.sujal.justask.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Database {
	private MongoClient mMongoClient;
	private MongoDatabase mDatabase;

	public static Database CONNECTED_DATABASE = new Database("justask");


	public Database(String databaseName) {
		mMongoClient = new MongoClient("localhost");
		mDatabase = mMongoClient.getDatabase(databaseName);
	}

	@Override
	public void finalize() {
		mMongoClient.close();
	}

	public void insertDocument(String collectionName, Document document) {
		MongoCollection<Document> collection = mDatabase.getCollection(collectionName);
		collection.insertOne(document);
	}

	public void updateDocument(String collectionName, Document filter, Document update) {
		MongoCollection<Document> collection = mDatabase.getCollection(collectionName);
		collection.updateOne(filter, new Document("$set", update));
	}

	public void deleteDocument(String collectionName, Document filter) {
		MongoCollection<Document> collection = mDatabase.getCollection(collectionName);
		collection.deleteOne(filter);
	}

	public Document findDocument(String collectionName, Document filter) {
		MongoCollection<Document> collection = mDatabase.getCollection(collectionName);
		return collection.find(filter).first();
	}


	public static void createUser(String name, String password, boolean isAdmin) {
		Document userDocument = new Document("name", name).append("password", password).append("isAdmin", isAdmin);
		CONNECTED_DATABASE.insertDocument("user", userDocument);
	}

	public static void createSurvey(String name, List<String> surveyQuestions) {
		List<Document> surveyAnswerList = new ArrayList<Document>();
		Document surveyDocument = new Document("name", name).append("questions", surveyQuestions)
				.append("surveyResponces", surveyAnswerList);

		CONNECTED_DATABASE.insertDocument("survey", surveyDocument);
	}

	public static void addSurveyAnswers(String surveyName, String username, List<String> answers) {
		Document filter = new Document("name", username);
		Document surveyAnswerDocument = new Document("surveyName", surveyName).append("answers", answers);

		Document update = new Document("$push", new Document("surveyResponses", surveyAnswerDocument));

		CONNECTED_DATABASE.updateDocument("user", filter, update);
	}

	public static boolean verifyUser(String username, String password) {
		Document filter = new Document("name", username);
		filter.append("password", password);

		Document userDocument = CONNECTED_DATABASE.findDocument("user", filter);

		return userDocument != null;
	}

	public static void main(String[] args) {
		createUser("admin", "admin", true);
	}
}
