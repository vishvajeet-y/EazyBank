package com.vish.account.constants;

//This will maintaining all constants values inside separate files.
public class AccountConstants {
    private AccountConstants(){
        /*
        We are creating private constructor because we don't want anyone creating the object of this class.
         */

    }
    /*
    Every constant has to be static and final so that no one can change them.At same time we can use
    these constant values without creating the object of this class.
     */
    public static  final String SAVING="savings";
    public static  final String ADDRESS="123 MainStreet,NewYork";
    public static  final String STATUS_201="201";
    public static  final String MESSAGE_201="Account Created Successfully";
    public static  final String STATUS_200="200";
    public static  final String MESSAGE_200="Request Processed Successfully";
    public static  final String STATUS_417="417";
    public static  final String MESSAGE_417_UPDATE="Update Operation Failed. Please try again or contact Dev Team";
    public static  final String MESSAGE_417_DELETE="Delete Operation Failed. Please try again or contact Dev Team";
    public static  final String STATUS_500="500";
    public static  final String MESSAGE_500="An error Occurred. Please try again or contact Dev Team";
}
