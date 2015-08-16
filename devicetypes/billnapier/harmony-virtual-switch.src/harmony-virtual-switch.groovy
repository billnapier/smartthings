metadata {
	
    definition (name: "Harmony Virtual Switch", namespace: "billnapier", author: "billnapier") {
		capability "Switch"
        capability "Momentary"
        capability "Polling"
	}

	tiles {
		standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state "off", label: '${currentValue}', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
			state "on", label: '${currentValue}', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#79b821"
		}
		standardTile("on", "device.switch", decoration: "flat") {
			state "default", label: 'On', action: "on", backgroundColor: "#ffffff"
		}
		standardTile("off", "device.switch", decoration: "flat") {
			state "default", label: 'Off', action: "off", backgroundColor: "#ffffff"
		}
		standardTile("toggle", "device.switch", decoration: "flat") {
			state "default", label: 'Toggle', action: "push", backgroundColor: "#ffffff"
		}
        attribute "switch", "string"
        main "switch"
		details(["switch","on","off"])
	}
}

private updateStatus() {
    // TODO(napier): we should do more, like update status
    poll()
}

def on() {
	log.debug "$version on()"
    updateStatus()
    poll()
    parent.on()
}

def off() {
	log.debug "$version off()"
    poll()
    parent.off()
}

def push() {
    log.debug "$version push"
}

def poll() {
    log.debug "poll"
    parent.getSwitchStatus()   
}

private getVersion() {
	"PUBLISHED"
}
