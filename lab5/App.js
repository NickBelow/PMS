import React from 'react';
import { NavigationContainer, DefaultTheme  } from '@react-navigation/native';
import RootNavigator from "./navigation/RootNavigator";
import { StatusBar  } from 'react-native';
StatusBar.setBarStyle('light-content')

const backColor = "#000000"

const MyTheme = {
  ...DefaultTheme,
  colors: {
    ...DefaultTheme.colors,
    primary: backColor,
  },
};

export default function App() {
  return (
    <NavigationContainer theme={MyTheme}>
      <RootNavigator />
    </NavigationContainer>
  )
}