import { Route, Routes, useNavigate } from "react-router-dom";
import "./App.css";
import Authentication from "./Components/Authentication/Authentication";
import HomePage from "./Components/HomePage/HomePage";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getUserProfile } from "./Store/Auth/Action";
import { createTheme, CssBaseline, ThemeProvider } from "@mui/material";

function App() {
  const jwt = localStorage.getItem("jwt");
  const { auth } = useSelector((store) => store);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const {mode} = useSelector((store)=>store.darkMode)

  // const appTheme = createTheme({
  //   palette: {
  //     mode: mode ? "dark" : "light",
  //   },
  // });

  useEffect(() => {
    if (jwt) {
      dispatch(getUserProfile(jwt));
      navigate("/");
    }
  }, [auth.jwt]);
  return (
//     <ThemeProvider theme={appTheme}>
// <CssBaseline>
<div style={{backgroundColor: mode? 'black': 'white', 
  color: mode? 'white': 'black'
}}>
<Routes>
        <Route
          path="/*"
          element={auth.user ? <HomePage /> : <Authentication />}
        ></Route>
      </Routes>
</div>

// </CssBaseline>
// </ThemeProvider>
      
  );
}

export default App;