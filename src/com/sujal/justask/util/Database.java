package com.sujal.justask.util;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	    List<List<Document>> surveyResponses = new ArrayList<>();

	    Document surveyDocument = new Document("name", name)
	            .append("questions", surveyQuestions)
	            .append("surveyResponses", surveyResponses);

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
	
	public static List<String> getAllSurveys() {
	    List<String> surveyList = new ArrayList<>();

	    MongoCollection<Document> collection = CONNECTED_DATABASE.mDatabase.getCollection("survey");
	    FindIterable<Document> surveys = collection.find();

	    for (Document survey : surveys) {
	        String surveyName = survey.getString("name");
	        surveyList.add(surveyName);
	    }

	    return surveyList;
	}
	
	public static Document getSurveyQuestionsAndAnswers(String surveyName) {
	    Map<String, List<String>> surveyData = new HashMap<>();
	    List<String> questions = new ArrayList<>();
	    List<String> answers = new ArrayList<>();
	    List<String> usernames = new ArrayList<>();

	    Document filter = new Document("name", surveyName);
	    Document surveyDocument = CONNECTED_DATABASE.findDocument("survey", filter);

	    return surveyDocument;
	}



	
	public static boolean verifyAdmin(String username, String password) {
		Document filter = new Document("name", username);
		filter.append("password", password);
		filter.append("isAdmin", true);

		Document userDocument = CONNECTED_DATABASE.findDocument("user", filter);

		return userDocument != null;
	}

	
	// only run when Admin needs to be created
	public static void main(String[] args) {
		if(! verifyAdmin("admin","admin")) {
			createUser("admin", "admin", true);
			System.out.println("NEW ADMIN CREATED");
		}
		else {
			System.out.println("ADMIN ALREADY EXISTS. RUN APPLICATION");
		}
		
	}
}
