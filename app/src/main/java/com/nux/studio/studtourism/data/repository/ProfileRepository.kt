package com.nux.studio.studtourism.data.repository

import android.graphics.Bitmap
import android.util.Log
import com.nux.studio.studtourism.data.error.ErrorCatcher
import com.nux.studio.studtourism.data.error.ErrorRemote
import com.nux.studio.studtourism.data.local.models.Dormitory
import com.nux.studio.studtourism.data.local.prefs.PhotoPrefs
import com.nux.studio.studtourism.data.remote.RetrofitServices
import com.nux.studio.studtourism.data.remote.models.EditUser
import com.nux.studio.studtourism.data.remote.models.User
import com.nux.studio.studtourism.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.awaitResponse
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val api: RetrofitServices,
    private val photoPrefs: PhotoPrefs,
) {

    private val _editUserFlow = MutableSharedFlow<Resource<User>>(
        replay = 3,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val editUserFlow = _editUserFlow.asSharedFlow()

    private val _profileFlow = MutableSharedFlow<Resource<User>>(
        replay = 3,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val profileFlow = _profileFlow.asSharedFlow()

    private val _uploadPhotoFlow = MutableSharedFlow<Resource<String>>(
        replay = 3,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val uploadPhotoFlow = _uploadPhotoFlow.asSharedFlow()

    private val defVal =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtxuuCDWzGaybpBF5fQJs6oTATHfPl42PH1JP2PH6D&s"

    fun getProfileUrl(): String = photoPrefs.url ?: defVal

    suspend fun loadProfile() {
        _profileFlow.emit(Resource.Loading(true))
        val request = api.getProfile()

        val response = try {
            val responseApi = request.awaitResponse()
            Log.d("RRR", "$responseApi")
            if (responseApi.code() == 200) {
                responseApi.body()
            } else {
                _profileFlow.emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                _profileFlow.emit(Resource.Loading(false))
                return
            }
        } catch (e: Exception) {
            Log.d("RRR", "$e")
            _profileFlow.emit(Resource.Error(message = ErrorRemote.NoInternet))
            _profileFlow.emit(Resource.Loading(false))
            return
        }

        photoPrefs.url = response?.avatar

        _profileFlow.emit(Resource.Success(response))
        _profileFlow.emit(Resource.Loading(false))
    }

    suspend fun editUser(
        id: String? = null,
        email: String? = null,
        gender: String? = null,
        firstName: String? = null,
        lastName: String? = null,
        middleName: String? = null,
        phone: String? = null,
        departureCity: String? = null,
        socialUrl: String? = null,
        universityName: String? = null,
        avatar: String? = null,
        birthday: String? = null,
        WoS: String? = null,
        WoS1: String? = null,
        studentRoleType: String? = null,
        starredDormitories: Set<String>? = null
    ) {
        _editUserFlow.emit(Resource.Loading(true))
        val editUser = EditUser(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            gender = gender,
            departureCity = departureCity,
            phone = phone,
            socialUrl = socialUrl,
            universityName = universityName,
            avatar = avatar,
            birthday = birthday,
            WoS = WoS,
            WoS1 = WoS1,
            studentRoleType = studentRoleType,
            starredDormitories = starredDormitories
        )

        val request = api.editUser(editUser)

        val response = try {
            val responseApi = request.awaitResponse()
            if (responseApi.code() == 200) {
                responseApi.body()
            } else {
                _editUserFlow.emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                _editUserFlow.emit(Resource.Loading(false))
                return
            }
        } catch (e: Exception) {
            _editUserFlow.emit(Resource.Error(message = ErrorRemote.NoInternet))
            _editUserFlow.emit(Resource.Loading(false))
            return
        }

        photoPrefs.url = response?.avatar

        _editUserFlow.emit(Resource.Loading(false))
        _editUserFlow.emit(Resource.Success(response))
    }

    suspend fun uploadImage(image: Bitmap) = withContext(Dispatchers.IO) {
        _uploadPhotoFlow.emit(Resource.Loading(true))
        val stream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        val body = MultipartBody.Part.createFormData(
            "file", "photo.png",
            byteArray.toRequestBody("image/png".toMediaTypeOrNull(), 0, byteArray.size)
        )

        val request = api.uploadPhoto(body)
        val response = try {
            val responseApi = request.awaitResponse()
            if (responseApi.code() == 200) {
                responseApi.body()
            } else {
                _uploadPhotoFlow.emit(Resource.Error(ErrorCatcher.catch(responseApi.code())))
                _uploadPhotoFlow.emit(Resource.Loading(false))
                return@withContext
            }
        } catch (e: Exception) {
            _uploadPhotoFlow.emit(Resource.Error(message = ErrorRemote.NoInternet))
            _uploadPhotoFlow.emit(Resource.Loading(false))
            return@withContext
        }

        val photoUrl = response?.url
        editUser(
            avatar = photoUrl
        )
        photoPrefs.url = response?.url
        _uploadPhotoFlow.emit(Resource.Loading(false))
        _uploadPhotoFlow.emit(Resource.Success(response?.url))
    }
}
