package com.ns.doctorplus.logic;

public final class UsersFirestoreDbContract {

    // Root collection name
    public static final String COLLECTION_NAME = "users";

    // Document ID
    public static final String DOCUMENT_ID = "document_id";

    // Document field names
    public static final String FIELD_FIRST_NAME = "firstName";
    public static final String FIELD_LAST_NAME = "lastName";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_BIRTH_DATE = "birthDate";
    public static final String FIELD_CNP = "cnp";
    public static final String FIELD_ADDRESS = "address";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_TYPE = "type";

    // To prevent someone from accidentally instantiating the contract 		class, make the constructor private
    private UsersFirestoreDbContract() {}
}
