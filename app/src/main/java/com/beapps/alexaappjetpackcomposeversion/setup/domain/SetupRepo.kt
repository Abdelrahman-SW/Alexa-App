package com.beapps.alexaappjetpackcomposeversion.setup.domain

import com.beapps.alexaappjetpackcomposeversion.setup.domain.models.SetupItem

interface SetupRepo {
    fun getSetupData () : List<SetupItem>
}