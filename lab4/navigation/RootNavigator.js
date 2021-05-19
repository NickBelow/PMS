import React from 'react';

import Account from "../screens/Account";
import Diagram from "../screens/Diagram";
import Movie from "../screens/Movie";

import { createStackNavigator } from '@react-navigation/stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { MaterialCommunityIcons } from '@expo/vector-icons';

import Create from "../modules/Create";
import Details from "../modules/Details";

import { styleConfig } from "../style";

const Stack = createStackNavigator();


const movieStackNavigator = () => {
    return(
        <Stack.Navigator initialRouteName="Movie">
            <Stack.Screen 
                name="Movie"
                component={Movie}
                options={{ ...styleConfig, title: 'Movie' }}
            />
            <Stack.Screen 
                name="Create"
                component={Create}
                options={{ ...styleConfig, title: 'Create your movie' }}
            />
            <Stack.Screen 
                name="Details"
                component={Details}
                options={{ ...styleConfig, title: 'Details' }}
            />
        </Stack.Navigator>
    )
}



const creatorStackNavigator = () => {
    return (
        <Stack.Navigator initialRouteName="Account">
            <Stack.Screen
                name="Account"
                component={Account}
                options={{ ...styleConfig, title: 'Creator' }}
            />
        </Stack.Navigator>
    )
}


const diagramStackNavigator = () => {
    return (
        <Stack.Navigator initialRouteName="Diagram">
            <Stack.Screen
                name="Diagram"
                component={Diagram}
                options={{ ...styleConfig, title: 'Charts' }}
            />
        </Stack.Navigator>
    )
}



const Tab = createBottomTabNavigator();

export default function RootNavigator() {
    return (
        <Tab.Navigator
            tabBarOptions={{ 
                labelStyle: { paddingBottom: 5 }, 
                style: { borderTopColor: styleConfig.footerStyle.shadowColor},
                activeTintColor: 'white',
                    activeBackgroundColor: styleConfig.footerStyle.backgroundColor,
                    inactiveBackgroundColor: styleConfig.footerStyle.backgroundColor }}>

            <Tab.Screen
                name="General"
                component={creatorStackNavigator}
                options={{
                    tabBarLabel: 'User',
                    tabBarIcon: ({ color, size }) => (
                        <MaterialCommunityIcons name="shield-check" color={color} size={size} />
                    ),
                }}
            />

            <Tab.Screen
                name="Diagrams"
                component={diagramStackNavigator}
                options={{
                    tabBarLabel: 'Diagram',
                    tabBarIcon: ({ color, size }) => (
                        <MaterialCommunityIcons name="graph" color={color} size={size} />
                    ),
                }}
            />

            <Tab.Screen
                name="Movies"
                component={movieStackNavigator}
                options={{
                    tabBarLabel: 'Movie',
                    tabBarIcon: ({ color, size }) => (
                        <MaterialCommunityIcons name="film" color={color} size={size} />
                    ),
                }}
            />
            
        </Tab.Navigator>
    );
}
