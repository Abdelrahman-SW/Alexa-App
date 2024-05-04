package com.beapps.alexaappjetpackcomposeversion.setup.data

import android.content.Context
import com.beapps.alexaappjetpackcomposeversion.core.data.loadJSONData
import com.beapps.alexaappjetpackcomposeversion.core.domin.getDrawableIdFromImageName
import com.beapps.alexaappjetpackcomposeversion.setup.domain.SetupRepo
import com.beapps.alexaappjetpackcomposeversion.setup.domain.getDrawableIdFromSetupTitle
import com.beapps.alexaappjetpackcomposeversion.setup.domain.models.SetupDetailItem
import com.beapps.alexaappjetpackcomposeversion.setup.domain.models.SetupItem
import com.beapps.alexaappjetpackcomposeversion.setup.domain.models.SetupType
import org.json.JSONArray
import org.json.JSONObject

class SetupRepoImpl(val context: Context) : SetupRepo {

    private val jsonStr by lazy { loadJSONData(context, "setup.json") }
    override fun getSetupData(): List<SetupItem> {
        jsonStr?.let { jsonStr ->
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

    override fun getSetupDetails(setupItem: SetupItem?): List<SetupDetailItem>? {

        jsonStr?.let { jsonStr ->
            setupItem?.let { setupItem ->
                val setupDetails: MutableList<SetupDetailItem> = mutableListOf()
                val jsonObject = JSONObject(jsonStr)
                val jsonArray = when (setupItem.type) {
                    SetupType.GROUPS -> jsonObject.getJSONArray("groups")
                    SetupType.MAIN_SETUP -> jsonObject.getJSONArray("mainsetup")
                }
                var setupDetailsJsonArray : JSONArray? = null
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val title = obj.getString("title")
                    if (title == setupItem.title) {
                        setupDetailsJsonArray = obj.getJSONArray("groupsSetup")
                        break
                    }
                }
                if (setupDetailsJsonArray == null) return null

                for (i in 0 until setupDetailsJsonArray.length()) {
                    val setupDetailObject = setupDetailsJsonArray.getJSONObject(i)
                    val imageName = try {
                        setupDetailObject.getString("image")
                    } catch (e: Exception) {
                        null
                    }
                    val content = try {
                        setupDetailObject.getString("text")
                    } catch (e: Exception) {
                        null
                    }
                    setupDetails.add(
                        SetupDetailItem
                            (
                            imageId = context.getDrawableIdFromImageName(imageName),
                            content = content
                        )
                    )
                }

                return setupDetails

            } ?: return null
        } ?: return null
    }
}