# rxpal
rxjava, retrofit packaging is used to process network requests

<h1>How to use?</h1>
<b>Step 1. Add the JitPack repository to your build file</b>

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


<b>Step 2. Add the dependency</b>


	dependencies {
	        compile 'com.github.hawkyAndroid:rxpal:0.1'
	}
  


<b>Step 3. Extends GeneralSubscriber（Built-In） Or Custom Subscriber</b>

for example:

public class MySubscriber extends GeneralSubscriber {

    public MySubscriber(Context context, boolean cancelable, CallBack callBack) {
        super(context, cancelable, callBack);
    }

    public MySubscriber(Context context, CallBack callBack) {
        super(context, callBack);
    }

    @Override
    public void onNext(String result) {
       // execute successful, do something...
       // callBack.onSuccess(new Gson(), data);...
       // callBack.onFailure(code, errMsg);...
    }

    @Override
    public void onError(Throwable e, int code) {
        super.onError(e, code);
       // execute failed, do something...
       // callBack.onFailure(code, errMsg);...
    }

}

<b>Step 4. You can init Rxpal in Application with onCreate() </b>

 RxPal.init("http://xxx.xxx.com/", true);
 
 <b>Step 5. At last, You can wrap it as a RestfulApi.</b>
 
 public class RestfulApi {

    /**
     * POST Request
     *
     * @param url        Request url
     * @param headers    Request headers
     * @param params     Request params
     * @param context    Context
     * @param cancelable whether or not can cancel request
     * @param callBack   callback interface
     */
     public static Subscription post(String url, Map<String, String> headers, Object params, Context context, boolean cancelable, GeneralSubscriber.CallBack callBack) {
        return RxPal.post(url, headers, params, new MySubscriber(context, cancelable, callBack));
    }
    
    // other warpped method for request...

}


<h1> Execution </h1>

    RestfulApi.post(url, headers, params, this, false, new GeneralSubscriber.CallBack() {
        @Override
        public void onSuccess(Gson gson, String s) {
            // Log.d(TAG,"s:" + s);
        }

        @Override
        public void onFailure(int code, String error) {
            // Log.d(TAG,"code:" + code + ",error:" + error);
        }
    });


