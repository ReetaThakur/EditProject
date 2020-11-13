### MutableLiveData

This is the most simplest LiveData, where it would just get updated and notify it’s observer.

```
// Declaring it
val liveDataA = MutableLiveData<String>()
// Trigger the value change
liveDataA.value = someValue
// Optionally, one could use liveDataA.postValue(value) 
// to get it set on the UI thread
```

Check out the below, even when the Fragment died, the LiveData value generate i.e. 8960 is not causing crashes due to setting on an inactive Fragment.

[![Watch the Video](https://drive.google.com/uc?export=view&id=1OnSeMY-6-LTtpQipuq2V6jP56ZVonoEM)](https://drive.google.com/uc?export=view&id=1gClZs6lvpY2ogAcqIfAWe8bIfvv2BuON)
