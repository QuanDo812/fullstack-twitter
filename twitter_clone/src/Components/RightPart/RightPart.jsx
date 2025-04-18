import React, { useState } from "react";
import SearchIcon from "@mui/icons-material/Search";
import Brightness4Icon from "@mui/icons-material/Brightness4";
import { Button, createTheme, ThemeProvider, useMediaQuery } from "@mui/material";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import SubscriptionModal from "../SubscriptionModal/SubscriptionModal";
import { useDispatch, useSelector } from "react-redux";

const RightPart = () => {
  const [openSubscriptionModal, setOpenSubscriptionModal] = React.useState(false);
  const handleOpenSubscriptionModal = () => setOpenSubscriptionModal(true);
  const handleCloseSubscriptionModal = () => setOpenSubscriptionModal(false);

  const {mode} = useSelector((store)=>store.darkMode)
  const dispatch = useDispatch()

  const handleChangeTheme = () => {
    dispatch({type: "TOGGLE_MODE", payload: {}})
    console.log("handle change theme");
  };

  return (
    
    <div className="py-5 sticky top" 
      style={{backgroundColor: mode? 'black': 'white'}}>
      <div className="relative flex items-center">
        <input
          type="text"
          className="py-3 rounded-full text-gray-500 w-full pl-12"
        />
        <div className="absolute top-0 left-0 pl-3 pt-3">
          <SearchIcon className="text-gray-500" />
        </div>
        <Brightness4Icon
          className="ml-3 cursor-pointer"
          onClick={handleChangeTheme}
        />
      </div>
      <section className="my-5">
        <h1 className="text-xl font-bold">Get Verified</h1>
        <h1 className="font-bold my-2">Subscribe to unlock new Features</h1>
        <Button
          onClick={handleOpenSubscriptionModal}
          variant="contained"
          sx={{ padding: "10px", paddingX: "20px", borderRadius: "25px" }}
        >
          Get Verified
        </Button>
      </section>
      <section className="mt-7 space-y-5">
        <h1 className="font-bold text-xl py-1">What's happening</h1>
        <div>
          <p className="text-sm"> FIFA Women's World Cup - Live </p>
          <p className="font-bold"> Philippines vs Switzerland </p>
        </div>
        {[1,1,1].map((item)=> <div className="flex justify-between w-full">
          <div>
            <p>Entertainment - Trending</p>
            <p className="font-bold">#TheMarvels</p>
            <p>16.3k Tweets</p>
          </div>
          <MoreHorizIcon />
        </div>)}
      </section>
      <section>
        <SubscriptionModal open={openSubscriptionModal} handleClose={handleCloseSubscriptionModal}/>
      </section>
      
    </div>
    
  );
};

export default RightPart;