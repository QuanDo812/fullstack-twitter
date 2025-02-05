import { applyMiddleware, combineReducers, legacy_createStore } from "redux";
import { thunk } from "redux-thunk";
import { authReducer } from "./Auth/Reducer";
import { twitReducer } from "./Twit/Reducer";
import { darkModeReducer } from "./DarkMode/Reducer";

const rootReducers = combineReducers({
  auth: authReducer,
  twit: twitReducer,
  darkMode: darkModeReducer
});

export const store = legacy_createStore(rootReducers, applyMiddleware(thunk));