package dto

import play.api.libs.json.Json

sealed trait Priority {
}

case object HIGH extends Priority {
}

case object LOW extends Priority {
}




