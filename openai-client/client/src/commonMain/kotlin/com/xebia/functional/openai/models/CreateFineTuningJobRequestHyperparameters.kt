/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.xebia.functional.openai.models


import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * The hyperparameters used for the fine-tuning job.
 *
 * @param batchSize 
 * @param learningRateMultiplier 
 * @param nEpochs 
 */
@Serializable

data class CreateFineTuningJobRequestHyperparameters (

    @SerialName(value = "batch_size") val batchSize: com.xebia.functional.openai.models.ext.finetune.job.create.CreateFineTuningJobRequestHyperparametersBatchSize? = null,

    @SerialName(value = "learning_rate_multiplier") val learningRateMultiplier: com.xebia.functional.openai.models.ext.finetune.job.create.CreateFineTuningJobRequestHyperparametersLearningRateMultiplier? = null,

    @SerialName(value = "n_epochs") val nEpochs: com.xebia.functional.openai.models.ext.finetune.job.create.CreateFineTuningJobRequestHyperparametersNEpochs? = null

)

