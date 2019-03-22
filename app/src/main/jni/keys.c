#include <jni.h>
JNIEXPORT jstring JNICALL
Java_com_sample_mvvmarchitecture_Utilities_Constants_getAPIKey(JNIEnv *env, jobject instance) {

 return (*env)->  NewStringUTF(env, "02e6471fb33bd2c190c22b6a0f61d8e5");
}

JNIEXPORT jstring JNICALL
Java_com_sample_mvvmarchitecture_RetrofitRepository_getBaseURL(JNIEnv *env, jobject instance) {

 return (*env)->  NewStringUTF(env, "https://api.themoviedb.org/3/");
}

