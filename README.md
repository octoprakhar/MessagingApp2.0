# MessagingApp2.0
This app is inspired from my older project But with upgraded error handling, clean architecture, dependency injection and uses MVI pattern(previous one was using MVVM).

**How files are arranged in a project?**
1. Directories are arranged based on their features. For example, there is a authentication feature, so every files/folder related to this feature is included in this directory.
2. After root directory, there is one more directory, the "core" directory. It handles all the common code files that will be shared between different features.
3. Each feature then divided into 3 + 1 sub-directory(or 3 + 1 layers):
     * **domain layer :**
         - This layer answers every "what" question of the feature. That means, questions like "what is a user?", "what are the features this user has?", and so on.
         - It is not dependent on any of the two layers. Because it doesn't contains the implementation part(The part that answers the question for "how?"). It is just contains abstraction for that implementation.
         - Suppose I want to register a new user in the cloud, So this layer will answer the question for "what are the things it will implement for this?". **Remember it doesn't answer "how", it answers "what"**
     * **data layer:**
         - This layer does everything related to the data required to make that particular feature.
         - It includes requesting of data, modifying the data for our use cases, storing the data etc.
     * **Presentation layer:**
         - As the name suggests, this layers handles everything related to the UI and UI logic.
         - It has no relation in handling business logic.
     * **DI (optional):**
         - All the dependency injections are stored here.

 **Error handling Mechanism:**
  * This project's error handling mechanisms depends on two things. First is generic "Result" function. and second "Error" interface.
    ```
    typealias DomainError = Error
    typealias EmptyResult<E> = Result<Unit, E>

    sealed interface Result<out D, out E: DomainError>{
        data class Success<out D>(val data: D) : Result<D,Nothing>
        data class Error<out E : DomainError>(val error : E) : Result<Nothing, E>
    }

    inline fun <T, E: DomainError,R> Result<T,E>.mapAResult(transform: (T) -> R): Result<R,E>{
        return when(this){
            is Result.Success -> Result.Success(transform(data))
            is Result.Error -> Result.Error(error)
        }
    }

    fun <T, E: DomainError> Result<T,E>.asEmptyDataResult(): EmptyResult<E>{
        return this.mapAResult {  }
    }

    inline fun <T, E: DomainError> Result<T,E>.onSuccess(action: (T)-> Unit): Result<T,E>{
        return when(this){
            is Result.Error -> this
            is Result.Success -> {
                action(data)
                this
            }

        }
    }

    inline fun <T, E: DomainError> Result<T,E>.onError(action: (E)-> Unit): Result<T,E>{
        return when(this){
            is Result.Success -> this
            is Result.Error -> {
                action(error)
                this
            }

        }
    }
    ```
  * For more information regarding error handlling [visit here]((https://github.com/octoprakhar/MessagingApp2.0/tree/main/ClientSide/messagesendingapp/core/domain/util)).

    
**Version and there features**->
1. Authentication features ->
   * This includes screens like registration and signIn.
   * Registration screen->
       - It first registers the user in Firebase authentication then firestore then local database.
       - It handles the errors related to the Registration or network error using enum classes classes.
         
