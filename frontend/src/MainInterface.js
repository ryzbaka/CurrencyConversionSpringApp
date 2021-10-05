import * as React from 'react';
import axios from "axios";
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import InputLabel from '@mui/material/InputLabel';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import Button from '@mui/material/Button';

import Swal from 'sweetalert2';

export default function MainInterface(){
    const [symbols, setSymbols] = React.useState([]);
    const [amount, setAmount] = React.useState(1)
    const [sourceSymbol, setSourceSymbol]=React.useState("EUR");
    const [targetSymbol, setTargetSymbol]=React.useState("USD");
    React.useEffect(()=>{
        axios.get("/getSymbols").then(({data})=>{
            setSymbols(data);
        })
    },[]);

    const handleSourceChange = (event)=>{
        setSourceSymbol(event.target.value);
    }
    const handleTargetChange = (event)=>{
        setTargetSymbol(event.target.value);
    }
    const convert = ()=>{
        console.log(`Source:${sourceSymbol}`)
        console.log(`Target:${targetSymbol}`)
        console.log(`Amount:${amount}`)
        if(amount&&sourceSymbol&&targetSymbol){
            axios.post("/convert",{
                amount:amount,
                source:sourceSymbol,
                target:targetSymbol    
            }).then(({data})=>{
                if(data.success){
                    Swal.fire({
                        title:"Conversion Successful!",
                        text:`Result is: ${data.value} ${data.currency}`,
                        icon:"success",
                        confirmButtonText:"Okay."
                    })
                }else{
                    Swal.fire({
                        title:"Conversion failed!",
                        text:data.message,
                        icon:"error",
                        confirmButtonText:"Okay."
                    }) 
                }
            })
        }else{
            Swal.fire({
                title:"Invalid input!",
                text:"Please make sure that input value is a number.",
                icon:"error",
                confirmButtonText:"Okay."
            })
        }
    }
    return (
        <div className="main-interface-container">
            <Box
                component="form"
                noValidate
                autoComplete="off"
                onSubmit={e=>e.preventDefault()}
            >
                <TextField  id="amount" label="Amount" onSubmit={e=>e.preventDefault()} variant="outlined" onKeyUp={(event)=>{
                    const value = parseFloat(event.target.value);
                    setAmount(value);
                }}/>
            </Box>
            <div className="symbol-container">
                <Box>
                    <FormControl
                        onSubmit={(e)=>e.preventDefault}
                    >
                        <InputLabel id="source-select-label">Source</InputLabel>
                        <Select
                        labelId="source-select-label"
                        id="source-select"
                        value={sourceSymbol}
                        label="Source"
                        onChange={handleSourceChange}
                        >
                            {symbols.map((symbol)=>{
                                return (
                                    <MenuItem value={symbol}>{symbol}</MenuItem>
                                )
                            })}
                        </Select>
                    </FormControl>
                </Box>
                <Box>
                    <FormControl
                        onSubmit={(e)=>e.preventDefault}
                    >
                        <InputLabel id="target-select-label">Target</InputLabel>
                        <Select
                        labelId="target-select-label"
                        id="target-select"
                        value={targetSymbol}
                        label="Target"
                        onChange={handleTargetChange}
                        >
                            {symbols.map((symbol)=>{
                                return (
                                    <MenuItem value={symbol}>{symbol}</MenuItem>
                                )
                            })}
                        </Select>
                    </FormControl>
                </Box>
            </div>
            <Button variant="contained" onClick={convert}>Submit</Button>
        </div>
    )
}