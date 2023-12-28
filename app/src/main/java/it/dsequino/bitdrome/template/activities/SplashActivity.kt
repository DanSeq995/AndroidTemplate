package it.dsequino.bitdrome.template.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import it.dsequino.bitdrome.template.R
import it.dsequino.bitdrome.template.api.ApiException
import it.dsequino.bitdrome.template.api.RetrofitBD
import it.dsequino.bitdrome.template.models.User
import it.dsequino.bitdrome.template.utils.FirebaseConfig
import it.dsequino.bitdrome.template.utils.Helper
import it.dsequino.bitdrome.template.utils.UserManager
import it.sermetra.cloud.laNuovaGuida.models.login.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class SplashActivity : AppCompatActivity(), RetrofitBD.OnPostLogin {
    private var user: User? = null
    private lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        userManager = UserManager.getInstance()
        user = userManager.loadUser(this)
        //fetchRemoteConfig()

        val oneSignalAppId = FirebaseConfig.ConfigValuesSingleton.configValues["ONESIGNAL_APP_ID_CLIENT"].toString()
        OneSignal.Debug.logLevel = LogLevel.VERBOSE
        OneSignal.initWithContext(this, oneSignalAppId)
        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }

        //if(user.id != null) {
            //External user registration
            //OneSignal.login(user.id!!)

            //Helper.startActivity(this, HomeActivity::class.java)
        //} else {
            //Helper.startActivity(this, LoginActivity::class.java)
        //}
    }

    //Retrieves all keys from Firebase Remote, it needs a connection to Firebase to work properly
    private fun fetchRemoteConfig() {
        FirebaseApp.initializeApp(this)
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.firebase_remote_config_default)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val remoteConfigValues = HashMap<String, String>()
                    remoteConfig.all.forEach { entry ->
                        remoteConfigValues[entry.key] = entry.value.asString()
                    }
                    FirebaseConfig.ConfigValuesSingleton.configValues = remoteConfigValues
                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onPostLogin(loginResponse: LoginResponse) {
        //Nei fragment: corregge l'errore Fragment not attached to a context
        /*if(isAdded && activity != null) {
            Toast.makeText(requireContext(), "Messaggio mandato", Toast.LENGTH_LONG).show()
        }*/
    }

    override fun onError(error: Throwable) {
        if (error is ApiException) {
            println(error)
        }
        error.printStackTrace()
    }
}