const initialState = {
    mode: JSON.parse(localStorage.getItem("mode")) || false, 
  };

export const darkModeReducer = (state = initialState, action) => {
    switch (action.type) {
        case "TOGGLE_MODE":
          const newMode = !state.mode;
          console.log(newMode?'black':'white')
          localStorage.setItem("mode", JSON.stringify(newMode)); // Lưu vào localStorage
          return { ...state, mode: newMode };
        default:
          return state;
      }
    
}