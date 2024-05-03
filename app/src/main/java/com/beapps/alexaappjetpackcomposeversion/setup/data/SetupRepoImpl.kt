package com.beapps.alexaappjetpackcomposeversion.setup.data

import android.content.Context
import com.beapps.alexaappjetpackcomposeversion.core.data.loadJSONData
import com.beapps.alexaappjetpackcomposeversion.setup.domain.SetupRepo
import com.beapps.alexaappjetpackcomposeversion.setup.domain.getDrawableIdFromSetupTitle
import com.beapps.alexaappjetpackcomposeversion.setup.domain.models.SetupItem
import com.beapps.alexaappjetpackcomposeversion.setup.domain.models.SetupType
import org.json.JSONObject

class SetupRepoImpl(context: Context) : SetupRepo {

    private val jsonStr by lazy { loadJSONData(context , "setup.json") }
    override fun getSetupData(): List<SetupItem> {
        jsonStr?.let {jsonStr->
            val jsonObject = JSONObject(jsonStr)
            val groupsJsonArray = jsonObject.getJSONArray("groups")
            val mainSetupJsonArray = jsonObject.getJSONArray("mainsetup")
            val setupItems: MutableList<SetupItem> = mutableListOf()

            for (i in 0 until groupsJsonArray.length()) {
                val groupObject = groupsJsonArray.getJSONObject(i)
                val title = groupObject.getString("title")
                setupItems.add(
                    SetupItem(
                        title = title,
                        iconId = title.getDrawableIdFromSetupTitle(),
                        type = SetupType.GROUPS
                    )
                )
            }

            for (i in 0 until mainSetupJsonArray.length()) {
                val mainSetupObject = mainSetupJsonArray.getJSONObject(i)
                val title = mainSetupObject.getString("title")
                setupItems.add(
                    SetupItem(
                        title = title,
                        iconId = title.getDrawableIdFromSetupTitle(),
                        type = SetupType.MAIN_SETUP
                    )
                )
            }
            return setupItems
        } ?: return listOf()
    }
}