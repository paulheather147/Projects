import { reactive } from 'vue';

export const userStore = reactive({
    currentUser: null,
});

export function setCurrentUser(user) {
    userStore.currentUser = {
        displayName: user.displayName,
        email: user.email,
        uid: user.uid
    };
    console.log("SET:", userStore.currentUser)
}

export function getCurrentUser() {
    console.log("GET:", userStore.currentUser)
    return userStore.currentUser;

}