#include <jni.h>
JNIEXPORT jstring JNICALL
Java_com_sample_mvvmarchitecture_Utilities_Constants_getAPIKey(JNIEnv *env, jobject instance) {

 return (*env)->  NewStringUTF(env, "Enter Your API Key Here");
}

JNIEXPORT jstring JNICALL
Java_com_sample_mvvmarchitecture_RetrofitRepository_getBaseURL(JNIEnv *env, jobject instance) {

 return (*env)->  NewStringUTF(env, "https://api.themoviedb.org/3/");
}

