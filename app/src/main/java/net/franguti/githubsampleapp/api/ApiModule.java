package net.franguti.githubsampleapp.api;

import android.util.Log;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import java.util.UUID;
import javax.inject.Singleton;
import net.franguti.githubsampleapp.Constants;
import net.franguti.githubsampleapp.dependencyinjection.Named;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Dagger module created to provide every dependency related to the API REST module.
 */
@Module(library = true) public class ApiModule {

  private static final String TAG = ApiModule.class.getSimpleName();

  @Provides @Named("github_endpoint") String provideSkyscannerEndpoint() {
    return Constants.GITHUB_HOST_ENDPOINT;
  }

  @Provides @Singleton OkHttpClient provideOkHttpClient() {
    OkHttpClient okHttpClient = new OkHttpClient();
    try {
      File cacheDir = new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString());
      Cache responseCache = new Cache(cacheDir, Constants.NETWORK_CACHE_SIZE);
      okHttpClient.setCache(responseCache);
    } catch (Exception e) {
      Log.e(TAG, "Error creating network cache system", e);
    }
    return okHttpClient;
  }

  @Provides Client provideClient(OkHttpClient okHttpClient) {
    return new OkClient(okHttpClient);
  }

  @Provides RestAdapter provideRestAdapter(Client client, @Named("github_endpoint") String endpoint) {
    return new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
        .setClient(client)
        .setEndpoint(endpoint)
        .build();
  }

}
